package okon.CPD_CACHE;

import java.time.LocalDateTime;

public class Message {
    private int startingFreeCacheSize;
    private int endingFreeCacheSize;
    private String executionTime;

    public Message(int startingFreeCacheSize, int endingFreeCacheSize, String executionTime) {
        this.startingFreeCacheSize = startingFreeCacheSize;
        this.endingFreeCacheSize = endingFreeCacheSize;
        this.executionTime = executionTime;
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

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }
}
