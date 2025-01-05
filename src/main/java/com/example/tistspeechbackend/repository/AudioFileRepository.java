package com.example.tistspeechbackend.repository;

import com.example.tistspeechbackend.entity.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AudioFileRepository extends JpaRepository<AudioFile, Long> {
    // 你可以在這裡添加自定義的查詢方法
    // 例如，根據 userId 查找音檔
    List<AudioFile> findByUserId(Long userId);
}
