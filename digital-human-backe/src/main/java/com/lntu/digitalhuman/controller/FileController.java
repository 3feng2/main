package com.lntu.digitalhuman.controller;

import com.lntu.digitalhuman.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
            
            String fileId = fileService.saveFile(file);
            return ResponseEntity.ok(fileId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId) {
        try {
            fileService.deleteFile(fileId);
            return ResponseEntity.ok("File deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Delete error: " + e.getMessage());
        }
    }
}