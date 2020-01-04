package com.codeinvestigator.springbootsftpmessagehandler.upload;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.io.File;

@MessagingGateway
public interface UploadMessagingGateway {

    @Gateway(requestChannel = "uploadfile")
    public void uploadFile(File file);
}
