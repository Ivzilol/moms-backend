package bg.mck.filestorageservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO {
    private final String id;
    private final String fileName;
    private final String fileUrl;
    private final String uploaderEmail;
    private final LocalDateTime uploadTime;
    private final String fileMatcher;

    private FileDTO(Builder builder) {
        this.id = builder.id;
        this.fileName = builder.fileName;
        this.fileUrl = builder.fileUrl;
        this.uploaderEmail = builder.uploaderEmail;
        this.uploadTime = builder.uploadTime;
        this.fileMatcher = builder.fileMatcher;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getUploaderEmail() {
        return uploaderEmail;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public String getFileMatcher() {
        return fileMatcher;
    }

    public static class Builder {
        private String id;
        private String fileName;
        private String fileUrl;
        private String uploaderEmail;
        private LocalDateTime uploadTime;
        private String fileMatcher;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder withFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public Builder withUploaderEmail(String uploaderEmail) {
            this.uploaderEmail = uploaderEmail;
            return this;
        }

        public Builder withUploadTime(LocalDateTime uploadTime) {
            this.uploadTime = uploadTime;
            return this;
        }

        public Builder withFileMatcher(String fileMatcher) {
            this.fileMatcher = fileMatcher;
            return this;
        }

        public FileDTO build() {
            return new FileDTO(this);
        }
    }
}

