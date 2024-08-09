package bg.mck.ordercommandservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
public class FileController {

    @Value("${APPLICATION_VERSION}")
    private String APPLICATION_VERSION;
    private final RestTemplate restTemplate;

    public FileController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileToOrder(@RequestPart("file") MultipartFile file) {

        String fileStorageServiceUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/"
                + APPLICATION_VERSION)
                .path("/files/upload")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultipartFile> requestEntity = new HttpEntity<>(file, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(fileStorageServiceUrl, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String fileUrl = response.getBody();

            return ResponseEntity.ok("File uploaded successfully: " + fileUrl);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        }
    }
}
