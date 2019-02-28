package edu.hashcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupUtil {

    public static List<Slide> groupInsideTag(Slide left, Slide right, String tag, Map<String, List<Slide>> slidesByTagMap, LinkedHashSet<Slide> slideShow) {

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

            Slide second = getBestSlide(first, slides);
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

    private static Slide getBestSlide(Slide current, List<Slide> slides) {
        Map<Slide, Integer> interestMap = new HashMap<>();
        for (Slide slide : slides) {
            Integer interest = InterestUtil.calculateInterest(current.getRight(), slide.getLeft());
            if (interest > 0) {
                interestMap.put(slide, interest);
            }
        }
        List<Slide> collect = interestMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return collect.isEmpty() ? null : collect.remove(0);
    }


}
