package com.example.tistspeechbackend.controller;

import com.example.tistspeechbackend.entity.AudioFile;
import com.example.tistspeechbackend.service.AudioFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/audio-files")
public class AudioFileController {

    private final AudioFileService audioFileService;

    @Autowired
    public AudioFileController(AudioFileService audioFileService) {
        this.audioFileService = audioFileService;
    }

    // 取得所有音檔
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<AudioFile> getAllAudioFiles() {
        return audioFileService.getAllAudioFiles();
    }

    // 根據 ID 取得單個音檔
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<AudioFile> getAudioFileById(@PathVariable Long id) {
        Optional<AudioFile> audioFile = audioFileService.getAudioFileById(id);
        return audioFile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 根據 userId 查找音檔
    @GetMapping("/user/{userId}")
    public List<AudioFile> getAudioFilesByUserId(@PathVariable Long userId) {
        return audioFileService.getAudioFilesByUserId(userId);
    }

    // 新增音檔
    @PostMapping
    public ResponseEntity<AudioFile> createAudioFile(@RequestBody AudioFile audioFile) {
        AudioFile savedAudioFile = audioFileService.saveAudioFile(audioFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAudioFile);
    }

    // 刪除音檔
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAudioFile(@PathVariable Long id) {
        audioFileService.deleteAudioFile(id);
        return ResponseEntity.noContent().build();
    }

    // 更新音檔的轉文字結果
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateTranscription/{id}")
    public ResponseEntity<AudioFile> updateTranscription(@PathVariable Long id, @RequestBody AudioFile updatedFile) {
        Optional<AudioFile> existingFile = audioFileService.getAudioFileById(id);

        if (existingFile.isPresent()) {
            AudioFile file = existingFile.get();
            file.setTranscription(updatedFile.getTranscription());  // 更新轉錄結果

            // 保存更新後的音檔
            AudioFile savedFile = audioFileService.saveAudioFile(file);
            return ResponseEntity.ok(savedFile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
