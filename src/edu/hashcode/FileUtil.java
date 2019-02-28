package edu.hashcode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    private static final String lineBreak = "\n";
    //    public static final String sourceFilePath = "/Users/andrey.komarov/work/ghash/data/a_example.txt";
    public static final String sourceFilePath = "/Users/andrey.komarov/work/ghash/data/c_memorable_moments.txt";
    public static final String resultFilePath = "/Users/andrey.komarov/work/ghash/data/c_memorable_moments.out.txt";

    static String[] read(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).split(lineBreak);
    }

    public String[] readObj() throws IOException {
        return read(sourceFilePath);
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

    static void writeResult(List<Slide> slides, String path) throws IOException {
        if (slides.isEmpty()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(slides.size());
        sb.append("\n");
        for (Slide slide : slides) {
            if (slide.getLeft().equals(slide.getRight())) {
                sb.append(slide.getLeft().id);
                sb.append("\n");
            } else {
                sb.append(slide.getLeft().id);
                sb.append(" ");
                sb.append(slide.getRight().id);
                sb.append("\n");
            }
        }

        writeResult(sb.toString(), path);
    }
}
