package com.haodemon.tasks.Storage;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;

public class SimpleFileReader implements IStorageReader<ArrayList<String>>  {
    @Override
    public ArrayList<String> read(File baseDir) {
        File f = new File(baseDir, StorageConstants.SIMPLE_TEXT_FILE);
        if (!f.exists()) return new ArrayList<>();
        try {
            return new ArrayList<String>(FileUtils.readLines(f));
        }
        catch (IOException e) {
            throw new UncheckedIOException("Failed to read the file " + f, e);
        }
    }
}
