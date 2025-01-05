package com.example.tistspeechbackend.service;

import com.example.tistspeechbackend.entity.AudioFile;
import com.example.tistspeechbackend.repository.AudioFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudioFileService {

    private final AudioFileRepository audioFileRepository;

    @Autowired
    public AudioFileService(AudioFileRepository audioFileRepository) {
        this.audioFileRepository = audioFileRepository;
    }

    // 取得所有音檔
    public List<AudioFile> getAllAudioFiles() {
        return audioFileRepository.findAll();
    }

    // 根據 ID 取得單個音檔
    public Optional<AudioFile> getAudioFileById(Long id) {
        return audioFileRepository.findById(id);
    }

    // 根據 userId 查找音檔
    public List<AudioFile> getAudioFilesByUserId(Long userId) {
        return audioFileRepository.findByUserId(userId);
    }

    // 保存音檔
    public AudioFile saveAudioFile(AudioFile audioFile) {
        return audioFileRepository.save(audioFile);
    }

    // 刪除音檔
    public void deleteAudioFile(Long id) {
        audioFileRepository.deleteById(id);
    }
}
