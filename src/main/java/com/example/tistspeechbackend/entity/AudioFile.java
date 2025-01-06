package com.example.tistspeechbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.sql.Timestamp;

@Entity
@Table(name = "audio_files")
public class AudioFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 唯一識別音檔

    @Column(name = "user_id", nullable = true)
    private Long userId;  // 關聯的使用者ID

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;  // 音檔名稱

    @Column(name = "file_type", length = 50)
    private String fileType;  // 音檔格式，例如 mp3, wav

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;  // 音檔資料，以 BLOB 存儲

    @Column(name = "uploaded_at", nullable = false, updatable = false)
    private Timestamp uploadedAt;  // 上傳時間

    @Column(name = "transcription", columnDefinition = "TEXT")
    private String transcription;  // 音檔文字轉錄結果

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
