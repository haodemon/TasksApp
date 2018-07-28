package com.haodemon.tasks.Storage;

import java.io.File;

public interface IStorageReader<T> {
    T read(File baseDir);
}
