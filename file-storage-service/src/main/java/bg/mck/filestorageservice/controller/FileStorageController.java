package bg.mck.filestorageservice.controller;

import bg.mck.filestorageservice.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/files")
public class FileStorageController {

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadFile(@RequestPart("files") MultipartFile file) {
        try {
            String fileId = fileStorageService.storeFile(file);

            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/")
                    .path(fileId)
                    .toUriString();

            ResponseEntity<String> ok = ResponseEntity.ok(fileUrl);
            return ok;
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable String id) {
        Optional<GridFsResource> resourceOptional = fileStorageService.getFileById(id);
        if (resourceOptional.isPresent()) {
            GridFsResource resource = resourceOptional.get();
            try {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(resource.getContentType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } catch (IllegalStateException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving file: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }
}
