package bg.mck.filestorageservice.controller;

import bg.mck.filestorageservice.dto.FileDTO;
import bg.mck.filestorageservice.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/${APPLICATION_VERSION}/user/files")
public class FileStorageController {

    private final RestTemplate restTemplate;

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final FileStorageService fileStorageService;

    public FileStorageController(RestTemplate restTemplate, FileStorageService fileStorageService) {
        this.restTemplate = restTemplate;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadFile(@RequestPart("files") MultipartFile file,
                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        String email;
        try {
            email = extractEmailFromToken(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("There was a problem getting the uploader`s email.");
        }


        try {
            return ResponseEntity.ok(fileStorageService.storeFile(file, email));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{fileName}/{id}")
    public ResponseEntity<?> getFile(@PathVariable String fileName, @PathVariable String id) {
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

    private String extractEmailFromToken(String token) {
        token = token.substring(7);
        return restTemplate
                .getForObject("http://authentication-service/" + APPLICATION_VERSION + "/authentication/getemail/" + token, String.class);
    }
}
