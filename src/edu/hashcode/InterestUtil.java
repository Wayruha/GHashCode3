package edu.hashcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// InterestUtil.calculateInterest(photos.get(0), photos.get(3));
public class InterestUtil {
    public static int calculateInterest(Photo photo1, Photo photo2) {

//        System.out.println("--> calculateInterest");
//        System.out.println("-> photo1=" + photo1.toString());
//        System.out.println("-> photo2=" + photo2.toString());

        Set<String> intersection = new HashSet<>(photo1.getTags());
        Set<String> photo1Tags = photo1.getTags();
        Set<String> photo2Tags = photo2.getTags();

        intersection.retainAll(photo2Tags);

        int common = intersection.size();
        int photo1Only = photo1Tags.size() - common;
        int photo2Only = photo2Tags.size() - common;

//        System.out.println("-> common=" + common);
//        System.out.println("-> photo1Only=" + photo1Only);
//        System.out.println("-> photo2Only=" + photo2Only);

        int interest = Integer.min(Integer.min(common, photo1Only), photo2Only);
//        System.out.println("-> interest=" + interest);

        return interest;
    }

    public static int calculateTotalInterest(List<Slide> slides) {
        int interest = 0;
        while (slides.size() > 1) {
            Slide current = slides.remove(0);
            Slide next = slides.get(0);

            int currentInterest = calculateInterest(current, next);
            if (currentInterest == 0) {
                System.out.println("WARNING. Interest is 0: " + current.getRight() + " " +  next.getLeft());
            }
            interest += currentInterest;
        }
        return interest;
    }

    public static int calculateInterest(Slide photo1, Slide photo2) {

        Set<String> intersection = new HashSet<>(photo1.getTags());
        Set<String> photo1Tags = photo1.getTags();
        Set<String> photo2Tags = photo2.getTags();

        intersection.retainAll(photo2Tags);

        int common = intersection.size();
        int photo1Only = photo1Tags.size() - common;
        int photo2Only = photo2Tags.size() - common;

        return Integer.min(Integer.min(common, photo1Only), photo2Only);
    }
}
