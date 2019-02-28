package edu.hashcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> popularityMap = new HashMap<>();
        Map<String, List<Photo>> photosByTagMap = new HashMap<>();

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
            Photo ph = new Photo(i - 1, hv.equals("H"));
            for (int j = 0; j < tagsQty; j++) {
                String tag = string[j + 2];
                tags.add(tag);
                Integer priority = popularityMap.getOrDefault(tag, 0);
                popularityMap.put(tag, ++priority);

                List<Photo> list = photosByTagMap.getOrDefault(tag, new ArrayList<>());
                list.add(ph);
                photosByTagMap.put(tag, list);
            }
            ph.setTags(tags);
            System.out.println(String.format("Added %s", ph.toString()));
            photos.add(ph);
        }

        List<String> tagListByPopularity = popularityMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        for (String tag : tagListByPopularity) {
//            System.out.println(String.format("Photo %s has value %d", k, popularityMap.get(k)));
            System.out.println(String.format(tag));
        }

        for (String tag : photosByTagMap.keySet()) {
            System.out.println(String.format("Tag %s has photos: %s", tag, photosByTagMap.get(tag)));
        }
    }
}
