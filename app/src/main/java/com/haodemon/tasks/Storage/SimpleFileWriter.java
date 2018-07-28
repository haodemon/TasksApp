package com.haodemon.tasks.Storage;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;

public class SimpleFileWriter implements IStorageWriter<ArrayList<String>> {
    @Override
    public boolean write(File baseDir, ArrayList<String> data) {
        File f = new File(baseDir, StorageConstants.SIMPLE_TEXT_FILE);
        try {
            FileUtils.writeLines(f, data);
            return true;
        }
        catch (IOException e) {
            throw new UncheckedIOException("Can't write to " + f, e);
        }
    }
}
