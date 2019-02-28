package edu.hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupUtil {

    public static List<Slide> groupInsideTag(Slide left, Slide right, String tag, Map<String, List<Slide>> slidesByTagMap, Map<Photo, Map<Photo, Integer>> interestMap) {

        List<Slide> slides = new ArrayList<>(slidesByTagMap.get(tag));
        List<Slide> result = new ArrayList<>();
        if (left != null) {
            slides.remove(left);
            result.add(left);
        }
        if (right != null) {
            slides.remove(right);
        }

        while (slides.size() > 0) {
            Slide first = slides.remove(0);
            result.add(first);

            Slide second = findBest(first, slides, interestMap);
            if (second != null) {
                slides.remove(second);
                result.add(second);
            }
        }

        if (right != null) {
            result.add(right);
        }
        return result;
    }

    private static Slide findBest(Slide first, List<Slide> slides, Map<Photo, Map<Photo, Integer>> interestMap) {
        return slides.isEmpty() ? null : slides.get(0);
    }
}
