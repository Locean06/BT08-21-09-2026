package vn.edu.hcmute.categoryapp.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.hcmute.categoryapp.service.FileStorageService;

@RestController
public class ImageUploadController {

    private final FileStorageService fileStorageService;

    public ImageUploadController(
            FileStorageService fileStorageService) {

        this.fileStorageService = fileStorageService;
    }

    @PostMapping(
            value = "/upload/category-image",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<Map<String, String>> uploadCategoryImage(
            @RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "message",
                                    "Vui lòng chọn ảnh."
                            )
                    );
        }

        String contentType = file.getContentType();

        if (
                contentType == null
                || !contentType.startsWith("image/")
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "message",
                                    "File được chọn phải là hình ảnh."
                            )
                    );
        }

        try {

            String fileName =
                    fileStorageService.save(
                            file,
                            "categories"
                    );

            String path =
                    "/uploads/categories/" + fileName;

            return ResponseEntity.ok(
                    Map.of(
                            "path",
                            path
                    )
            );

        } catch (IOException e) {

            return ResponseEntity
                    .status(
                            HttpStatus.INTERNAL_SERVER_ERROR
                    )
                    .body(
                            Map.of(
                                    "message",
                                    "Không thể lưu ảnh."
                            )
                    );
        }
    }
}