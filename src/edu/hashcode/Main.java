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
        Integer numberOfPhotos = Integer.valueOf(qty);

        List<Photo> photos = new ArrayList<>();
        for (int i = 1; i <= numberOfPhotos; i++) {
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
//            System.out.println(String.format("Added %s", ph.toString()));
            photos.add(ph);
        }

        List<String> tagListByPopularity = popularityMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Map<Photo, Map<Photo, Integer>> interestMap = prepareRelatedPhotosInterestMap(numberOfPhotos, photos);

        Map<String, List<Slide>> stringListMap = groupSlides(tagListByPopularity, photosByTagMap);
//        printPopularityList(tagListByPopularity);
        printPhotosByTag(tagListByPopularity, photosByTagMap);
//        printInterestConnections(interestMap);


    }

    private static Map<Photo, Map<Photo, Integer>> prepareRelatedPhotosInterestMap(Integer numberOfPhotos, List<Photo> photos) {
        Map<Photo, Map<Photo, Integer>> interestMap = new HashMap<>();
        for (int i = 0; i < numberOfPhotos; i++) {
            Photo currentPhoto = photos.get(i);
            for (int j = i + 1; j < numberOfPhotos; j++) {
                Photo nextPhoto = photos.get(j);
                Integer interest = InterestUtil.calculateInterest(currentPhoto, nextPhoto);
                if (interest > 0) {
                    Map<Photo, Integer> interestSub = interestMap.getOrDefault(currentPhoto, new HashMap<>());
                    interestSub.put(nextPhoto, interest);
                    interestMap.put(currentPhoto, interestSub);
                }
            }
        }
        return interestMap;
    }

    private static void printInterestConnections(Map<Photo, Map<Photo, Integer>> interestMap) {
        for (Photo photo : interestMap.keySet()) {
            System.out.println(String.format("photo %s has following friends:", photo));

            Map<Photo, Integer> relatedPhotoMap = interestMap.get(photo);
            for (Photo relatedPhoto : relatedPhotoMap.keySet()) {
                System.out.println(String.format("\tinterest: %d, %s", relatedPhotoMap.get(relatedPhoto), relatedPhoto));
            }
        }
    }

    private static void printPhotosByTag(List<String> tagListByPopularity, Map<String, List<Photo>> photosByTagMap) {
        for (String tag : tagListByPopularity) {
//            System.out.println(String.format("Tag %s has photos: %s", tag, photosByTagMap.get(tag)));
            long horizontalCount = photosByTagMap.get(tag).stream().filter(Photo::isHorizontal).count();
            System.out.println(String.format("Tag %s has %d horizontal and %d vertical photos", tag,
                    horizontalCount, photosByTagMap.get(tag).size() - horizontalCount));
        }
    }

    private static Map<String, List<Slide>> groupSlides(List<String> tagListByPopularity, Map<String, List<Photo>> photosByTagMap) {
        Map<String, List<Slide>> slideByTagMap = new HashMap<>();
        for (String tag : tagListByPopularity) {
            List<Photo> photosForThisTag = photosByTagMap.get(tag);
            List<Photo> verticalPhotos = photosForThisTag.stream().filter(Photo::isHorizontal).collect(Collectors.toList());
            List<Photo> horizontalPhotos = photosForThisTag.stream().filter(p -> !p.horizontal).collect(Collectors.toList());

            List<Slide> slides = new ArrayList<>();
            slideByTagMap.put(tag, slides);
            while (verticalPhotos.size() > 1) {
                Photo left = verticalPhotos.remove(0); //todo find best match
                Photo right = verticalPhotos.remove(0);
                Slide slide = new Slide(left, right);
                slides.add(slide);
            }

            for (Photo horizontalPhoto : horizontalPhotos) {
                Slide slide = new Slide(horizontalPhoto);
                slides.add(slide);
            }
        }
        return slideByTagMap;
    }

    private static void printPopularityList(List<String> tagListByPopularity) {
        for (String tag : tagListByPopularity) {
            System.out.println(String.format(tag));
        }
    }

}
