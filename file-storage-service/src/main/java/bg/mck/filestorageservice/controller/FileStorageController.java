package bg.mck.filestorageservice.controller;

import bg.mck.filestorageservice.service.FileStorageService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/files")
public class FileStorageController {

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final GridFsTemplate gridFsTemplate;
    private final FileStorageService fileStorageService;

    public FileStorageController(GridFsTemplate gridFsTemplate, FileStorageService fileStorageService) {
        this.gridFsTemplate = gridFsTemplate;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileId = fileStorageService.storeFile(file);

            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/")
                    .path(fileId)
                    .toUriString();

            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GridFsResource> getFile(@PathVariable String id) {
        GridFsResource resource = fileStorageService.getFileById(id);
        if (resource != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(resource.getContentType()))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
