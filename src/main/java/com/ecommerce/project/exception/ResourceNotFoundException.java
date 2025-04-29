package com.ecommerce.project.exception;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fileId;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resourceName, String fileId, String fieldName) {
        super(String.format("%s not found with %s: %s",resourceName,fileId,fieldName));
        this.resourceName = resourceName;
        this.fileId = fileId;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String resourceName, String fileId, Long fieldId) {
        super(String.format("%s not found with %s: %s",resourceName,fileId,fieldId));
        this.resourceName = resourceName;
        this.fileId = fileId;
        this.fieldId = fieldId;
    }




}
