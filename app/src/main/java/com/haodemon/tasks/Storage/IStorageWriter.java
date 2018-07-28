package com.haodemon.tasks.Storage;

import java.io.File;

public interface IStorageWriter<T> {
    boolean write(File baseDir, T data);
}
