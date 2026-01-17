package com.lntu.digitalhuman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final GridFsTemplate gridFsTemplate;

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        
        return gridFsTemplate.store(
            file.getInputStream(), 
            originalFilename, 
            contentType
        ).toString();
    }

    public void deleteFile(String fileId) {
        gridFsTemplate.delete(new org.springframework.data.mongodb.core.query.Query(
            org.springframework.data.mongodb.core.query.Criteria.where("_id").is(fileId)
        ));
    }
}