package com.haodemon.tasks.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleStorage {
    private static final String SIMPLE_TEXT_FILE = "storage";
    private static final String TEMP_FILE_PREFIX = "temp";

    public static <T> List<T> read(File storagePath) {
        File storageFile = new File(storagePath, SIMPLE_TEXT_FILE);
        if (!storageFile.exists()) return new ArrayList<>();

        try (FileInputStream is = new FileInputStream(storageFile);
             ObjectInputStream os = new ObjectInputStream(is)) {
            // noinspection unchecked
            return (List<T>) os.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Unable to open file", e);
        }
    }

    // todo call method on pause
    // todo add cache
    public static <T extends Serializable> void write(File storagePath, List<T> data) {
        try {
            File temp = File.createTempFile(TEMP_FILE_PREFIX, null, storagePath);
            try (FileOutputStream fs = new FileOutputStream(temp);
                 ObjectOutputStream os = new ObjectOutputStream(fs)) {
                os.writeObject(data);
            }
            File storageFile = new File(storagePath, SIMPLE_TEXT_FILE);
            if (!temp.renameTo(storageFile)) {
                throw new IOException("Unable to move " + temp.getAbsolutePath() + " to " + storageFile.getAbsolutePath());
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to save tasks", e);
        }
    }
}
