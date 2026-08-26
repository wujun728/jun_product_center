package com.ruoyi.file.storage.write;


import com.ruoyi.file.storage.write.domain.WriteFile;

import java.io.InputStream;

public abstract class Writer {
    public abstract void write(InputStream inputStream, WriteFile writeFile);
}
