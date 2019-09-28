package com.example.pathhousingneeds;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataString {
    public static String JString;

    static {
        try {
            JString = readFile("com/example/pathhousingneeds/myFile.txt", Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
