package edu.hashcode;

import java.util.Set;

// InterestUtil.calculateInterest(photos.get(0), photos.get(1));
public class InterestUtil {
    public static int calculateInterest(Photo photo1, Photo photo2) {

        System.out.println("--> calculateInterest");
        System.out.println("-> photo1="+photo1.toString());
        System.out.println("-> photo2="+photo2.toString());

        Set<String> photo1Tags = photo1.getTags();
        Set<String> photo2Tags = photo2.getTags();

        int common = 0;

        for(String tag1 : photo1Tags) {
            for(String tag2 : photo2Tags) {
                if(tag1.equals(tag2)) {
                    common++;
                }
            }
        }

        System.out.println("->common="+common);

        return 0;
    }
}
