package com.example.springdemo.config;

import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfiguration implements WebMvcConfigurer, CommandLineRunner {

    @Value("${value.filePath}")
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/test/**")
                .addResourceLocations("file:"+rootPath+"/test/");
        registry.addResourceHandler("/file/cv/**")
                .addResourceLocations("file:"+rootPath+"/cv/");
    }

    @Override
    public void run(String... args) {
        createFolder("/test");
        createFolder("/cv");
    }

    private void createFolder(String path) {
        File folder = new File(rootPath + path);
        boolean folderExist = folder.exists() && folder.isDirectory();
        log.info("create folder {} status is {} ", folder, folderExist);
        if (!folderExist) {
            boolean isCreated = folder.mkdirs();
            log.info("create status {}", isCreated);
        }
    }
}
