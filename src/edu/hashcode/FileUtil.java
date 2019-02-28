package edu.hashcode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    private static final String lineBreak = "\n";
    private static final String resultFilePath = "/Users/roman/Desktop/a_example.out";

    static String[] read(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).split(lineBreak);
    }

    public Object readObj(String path) throws IOException {
        read(path);
        return new Object();
    }

    static void writeResult(String content, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
        printWriter.close();
    }

    static void writeResult(String content) throws IOException {
        writeResult(content, resultFilePath);
    }
}
