package bg.mck.ordercommandservice.dto;

import java.time.LocalDateTime;

public class FileDTO {

    private String id;
    private String fileName;
    private String fileUrl;
    private String uploaderEmail;
    private LocalDateTime uploadTime;
    private String fileMatcher;

    public FileDTO() {
    }

    public FileDTO(String id, String fileName, String fileUrl, String uploaderEmail, LocalDateTime uploadTime, String fileMatcher) {
        this.id = id;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.uploaderEmail = uploaderEmail;
        this.uploadTime = uploadTime;
        this.fileMatcher = fileMatcher;
    }

    public String getId() {
        return id;
    }

    public FileDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileDTO setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public FileDTO setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public String getUploaderEmail() {
        return uploaderEmail;
    }

    public FileDTO setUploaderEmail(String uploaderEmail) {
        this.uploaderEmail = uploaderEmail;
        return this;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public FileDTO setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
        return this;
    }

    public String getFileMatcher() {
        return fileMatcher;
    }

    public FileDTO setFileMatcher(String fileMatcher) {
        this.fileMatcher = fileMatcher;
        return this;
    }
}

