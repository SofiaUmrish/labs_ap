package com.droid_game.record_battle;

import java.io.FileWriter;
import java.io.IOException;

public class RecordBattle {
    private FileWriter fileWriter;
    private String logFilePath;

    public RecordBattle(String logFilePath) throws IOException {
        this.logFilePath = logFilePath;
        this.fileWriter = new FileWriter(logFilePath, true); // true для додавання до існуючого файлу
    }

    public void log(String message) throws IOException {
        fileWriter.write(message + "\n");
    }

    public void close() throws IOException {
        if (fileWriter != null) {
            fileWriter.close();
        }
    }

    @Override
    public String toString() {
        return logFilePath;
    }
}
