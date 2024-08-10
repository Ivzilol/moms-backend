package bg.mck.filestorageservice.service;

import bg.mck.filestorageservice.dto.FileDTO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FileStorageService {

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final GridFsTemplate gridFsTemplate;

    public FileStorageService(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    public FileDTO storeFile(MultipartFile file, String email) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("fileName", file.getOriginalFilename());
        metaData.put("contentType", file.getContentType());

        ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + APPLICATION_VERSION)
                .path("/user/files/")
                .path(file.getOriginalFilename() + "/")
                .path(id.toString())
                .toUriString();

        return new FileDTO.Builder()
                .withId(id.toString())
                .withFileName(file.getOriginalFilename())
                .withFileUrl(fileDownloadUri)
                .withUploaderEmail(email)
                .withUploadTime(LocalDateTime.now())
                .build();
    }

    public Optional<GridFsResource> getFileById(String id) {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (file != null) {
            GridFsResource resource = gridFsTemplate.getResource(file);
            return Optional.of(resource);
        }
        return Optional.empty();
    }
}

