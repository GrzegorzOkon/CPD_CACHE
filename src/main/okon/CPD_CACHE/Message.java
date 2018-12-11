package okon.CPD_CACHE;

import java.time.LocalDateTime;

public class Message {
    private int startingFreeCacheSize;
    private int endingFreeCacheSize;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Message(int startingFreeCacheSize, int endingFreeCacheSize, LocalDateTime startTime, LocalDateTime endTime) {
        this.startingFreeCacheSize = startingFreeCacheSize;
        this.endingFreeCacheSize = endingFreeCacheSize;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartingFreeCacheSize() {
        return startingFreeCacheSize;
    }

    public void setStartingFreeCacheSize(int startingFreeCacheSize) {
        this.startingFreeCacheSize = startingFreeCacheSize;
    }

    public int getEndingFreeCacheSize() {
        return endingFreeCacheSize;
    }

    public void setEndingFreeCacheSize(int endingFreeCacheSize) {
        this.endingFreeCacheSize = endingFreeCacheSize;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
