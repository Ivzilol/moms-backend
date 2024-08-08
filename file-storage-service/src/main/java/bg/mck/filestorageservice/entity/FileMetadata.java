package bg.mck.filestorageservice.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fileMetadata")
public class FileMetadata {
    @Id
    private String id;
    private String fileName;
    private String contentType;
    private long size;

    public FileMetadata() {
    }

    public FileMetadata(String id, String fileName, String contentType, long size) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public FileMetadata setId(String id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileMetadata setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public FileMetadata setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public long getSize() {
        return size;
    }

    public FileMetadata setSize(long size) {
        this.size = size;
        return this;
    }
}
