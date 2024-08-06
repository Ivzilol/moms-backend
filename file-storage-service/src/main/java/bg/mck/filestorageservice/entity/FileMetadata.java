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
}
