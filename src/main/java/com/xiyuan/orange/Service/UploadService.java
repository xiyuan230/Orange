package com.xiyuan.orange.Service;

import com.xiyuan.orange.Common.R;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
public class UploadService {
    @Value("${spring.resources.static-locations}")
    private String FILE_PATH;
    private String RESOURCE_URL = "http://192.168.31.190:8080/static/";
    public String uploadFile(MultipartFile uploadFile) {
        String fileName = uploadFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        String currentDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now());

        try {
            File file = new File(FILE_PATH + currentDate + "\\\\" + fileName);
            file.getParentFile().mkdir();
            if (!file.exists()) {
                file.createNewFile();
            }
            uploadFile.transferTo(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
        return RESOURCE_URL + currentDate+ "/"+fileName;
    }
}
