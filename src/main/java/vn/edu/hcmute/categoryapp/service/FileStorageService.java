package vn.edu.hcmute.categoryapp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final Path uploadRoot = Paths.get("uploads");

    public String save(MultipartFile file, String folder) throws IOException {

        if (file == null || file.isEmpty()) {
            return null;
        }

        Path directory = uploadRoot.resolve(folder);

        Files.createDirectories(directory);

        String originalName = file.getOriginalFilename();

        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(
                    originalName.lastIndexOf(".")
            );
        }

        String fileName =
                UUID.randomUUID().toString() + extension;

        Path target =
                directory.resolve(fileName).normalize();

        Files.copy(
                file.getInputStream(),
                target,
                StandardCopyOption.REPLACE_EXISTING
        );

        return fileName;
    }

    public void delete(String folder, String fileName) {

        if (fileName == null || fileName.isBlank()) {
            return;
        }

        try {

            Path directory =
                    uploadRoot
                            .resolve(folder)
                            .toAbsolutePath()
                            .normalize();

            Path file =
                    directory
                            .resolve(fileName)
                            .normalize();

            if (!file.startsWith(directory)) {
                return;
            }

            Files.deleteIfExists(file);

        } catch (IOException e) {

            System.err.println(
                    "Không thể xóa file: " + fileName
            );
        }
    }
}