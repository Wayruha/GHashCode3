package edu.hashcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> priorityMap = new HashMap<>();

        FileUtil fu = new FileUtil();

        String[] strings = fu.readObj();

        String qty = strings[0];
        System.out.println(String.format("Having %s photos", qty));
        Integer n = Integer.valueOf(qty);

        List<Photo> photos = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String[] string = strings[i].split(" ");
            String hv = string[0];
            Integer tagsQty = Integer.valueOf(string[1]);
            Set<String> tags = new HashSet<>();
            for (int j = 0; j < tagsQty; j++) {
                String tag = string[j + 2];
                tags.add(tag);
            }
            Photo ph = new Photo(i - 1, hv.equals("H"), tags);
            System.out.println(String.format("Added %s", ph.toString()));
            photos.add(ph);
        }
        System.out.println(String.format("%s", 1));
    }
}
