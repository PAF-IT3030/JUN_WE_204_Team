package com.post.system;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get("C:\\Users\\Sihas\\Downloads\\spring\\system\\src\\main\\resources\\static\\" + uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            String serverAddress = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            String fileUrl = serverAddress + "/" + uploadDir + "/" + fileName;

            return fileUrl;
        } catch (IOException ioException) {
            // Handle exception
            ioException.printStackTrace();
            throw ioException;
        }
    }
}
