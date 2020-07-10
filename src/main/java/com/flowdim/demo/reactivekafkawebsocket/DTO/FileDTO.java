package com.flowdim.demo.reactivekafkawebsocket.DTO;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class FileDTO {
    private String fileName;
    private String filePath;
    private FileTime modifiedDate;

    public FileDTO() {
    }

    public FileDTO(String fileName, String filePath, FileTime modifiedDate) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.modifiedDate = modifiedDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(FileTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
