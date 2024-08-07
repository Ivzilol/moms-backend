package bg.mck.filestorageservice.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class FileStorageService {


    private final GridFsTemplate gridFsTemplate;

    public FileStorageService(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    public String storeFile(MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("fileName", file.getOriginalFilename());
        metaData.put("contentType", file.getContentType());

        ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);

        return id.toString();
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

