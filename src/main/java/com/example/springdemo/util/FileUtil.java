package com.example.springdemo.util;

import com.example.springdemo.exception.FileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FileUtil {


    @Value("${value.filePath}")
    private String rootPath;

    public String save(MultipartFile file, String filePath) {
        try {
            String fileFormat = Objects.requireNonNull(file.getOriginalFilename())
                    .substring(Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf('.'));

            String fileName = getFileName();

            fileName=fileName+"test";

            String filePathBuilder = rootPath +
                    filePath +
                    fileName +
                    fileFormat;

            Path path= Paths.get(filePathBuilder);
            byte[] bytes=file.getBytes();
            Files.write(path,bytes);

            return  fileName+fileFormat;
        } catch (Exception ex){
            throw  new FileException("File save process is failed. filePath: "+filePath);
        }
    }

    public void deleteFile(String path){
        try {
            Files.deleteIfExists(Path.of(rootPath+path));
        } catch (IOException e) {
            throw new FileException("File delete process is failed. filePath: "+rootPath+path);
        }
    }

    private static String getFileName() {
        return String.format("%s_%s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss")),
                getRandomName());
    }

    private static String getRandomName() {
        System.out.println(UUID.randomUUID());
        return UUID.randomUUID().toString().substring(0, 7).replace("-", "");
    }
}
