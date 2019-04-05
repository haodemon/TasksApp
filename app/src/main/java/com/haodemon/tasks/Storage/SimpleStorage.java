package com.haodemon.tasks.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleStorage {
    private static final String SIMPLE_TEXT_FILE = "storage";

    public static <T> List<T> read(File storagePath) {
        File storageFile = new File(storagePath, SIMPLE_TEXT_FILE);
        if (!storageFile.exists()) return new ArrayList<>();

        try (FileInputStream is = new FileInputStream(storageFile);
             ObjectInputStream os = new ObjectInputStream(is)) {
            // noinspection unchecked
            return (List<T>) os.readObject();
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to open file", e);
        }
    }

    public static <T extends Serializable> void write(File storagePath, List<T> data) {
        try (FileOutputStream fs = new FileOutputStream(new File(storagePath, SIMPLE_TEXT_FILE));
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(data);
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to save tasks", e);
        }
    }
}
