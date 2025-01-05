package com.example.tistspeechbackend.controller;

import com.example.tistspeechbackend.entity.AudioFile;
import com.example.tistspeechbackend.repository.AudioFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @Autowired
    private AudioFileRepository audioFileRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("檔案不可為空");
        }

        try {
            // 將檔案資料轉換為 byte[]
            byte[] fileData = file.getBytes();

            // 儲存檔案資訊到資料庫
            AudioFile audioFile = new AudioFile();
            audioFile.setFileName(file.getOriginalFilename());
            audioFile.setFileData(fileData);
            audioFile.setUploadedAt(Timestamp.valueOf(LocalDateTime.now()));
            audioFileRepository.save(audioFile);

            return ResponseEntity.ok("檔案上傳成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("上傳失敗");
        }
    }
}
