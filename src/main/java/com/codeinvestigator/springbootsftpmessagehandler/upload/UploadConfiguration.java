package com.codeinvestigator.springbootsftpmessagehandler.upload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.time.LocalDateTime;

@Configuration
public class UploadConfiguration {

    public DefaultSftpSessionFactory gimmeFactory(){
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost("0.0.0.0");
        factory.setPort(22);
        factory.setAllowUnknownKeys(true);
        factory.setUser("mike");
        factory.setPassword("password123");
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "uploadfile")
    MessageHandler uploadHandler(){
        SftpMessageHandler messageHandler = new SftpMessageHandler(gimmeFactory());
        messageHandler.setRemoteDirectoryExpression(new LiteralExpression("/upload"));
        messageHandler.setFileNameGenerator(message -> String.format("mytextfile_%s.txt", LocalDateTime.now()));
        return messageHandler;
    }

}
