package com.du.lin.utils;

import java.io.*;

public class FileUtil {

    public static boolean copy(File file, File newFile) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileOutputStream out = new FileOutputStream(newFile);
        byte[] buffer = new byte[1028 * 10];
        while (true) {
            int ins = in.read(buffer);
            if (ins == -1) {
                in.close();
                out.flush();
                out.close();
                return newFile.exists();
            } else {
                out.write(buffer, 0, ins);
            }
        }
    }
}