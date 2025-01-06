package com.example.tistspeechbackend.controller;

import com.example.tistspeechbackend.entity.AudioFile;
import com.example.tistspeechbackend.repository.AudioFileRepository;
import com.example.tistspeechbackend.dto.UploadResponse;  // 引入 UploadResponse
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<UploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            // 檔案為空時回傳錯誤訊息
            UploadResponse response = new UploadResponse("error", "檔案不可為空");
            return ResponseEntity.badRequest().body(response);
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

            // 檔案成功上傳
            UploadResponse response = new UploadResponse("success", "檔案上傳成功");
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            // 上傳過程中發生錯誤
            UploadResponse response = new UploadResponse("error", "上傳失敗");
            return ResponseEntity.status(500).body(response);
        }
    }
}
