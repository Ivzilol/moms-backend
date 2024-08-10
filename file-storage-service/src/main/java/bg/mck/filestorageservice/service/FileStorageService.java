package bg.mck.filestorageservice.service;

import bg.mck.filestorageservice.dto.FileDTO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileStorageService {

    private final Logger LOGGER = LoggerFactory.getLogger(FileStorageService.class);

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
        String fileMatchingPattern = getFileMatchingPattern(file.getOriginalFilename());
        String fileNameWithoutPattern = removeFileMatchingPattern(file.getOriginalFilename());

        ObjectId id = gridFsTemplate.store(file.getInputStream(), fileNameWithoutPattern, file.getContentType(), metaData);


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + APPLICATION_VERSION)
                .path("/user/files/")
                .path(fileNameWithoutPattern + "/")
                .path(id.toString())
                .toUriString();

        return new FileDTO.Builder()
                .withId(id.toString())
                .withFileName(fileNameWithoutPattern)
                .withFileMatcher(fileMatchingPattern)
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

    private static String getFileMatchingPattern(String fileName) {
        String pattern = "(\\d{3})(?=\\.[^\\.]+$)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(fileName);

        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }

    public static String removeFileMatchingPattern(String fileName) {
        String pattern = "(_\\d{3})(?=\\.[^\\.]+$)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(fileName);

        if (m.find()) {
            return fileName.replaceFirst(pattern, "");
        } else {
            return fileName;
        }
    }
}

