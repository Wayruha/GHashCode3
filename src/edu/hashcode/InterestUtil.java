package edu.hashcode;

import java.util.Set;

// InterestUtil.calculateInterest(photos.get(0), photos.get(3));
public class InterestUtil {
    public static int calculateInterest(Photo photo1, Photo photo2) {

        System.out.println("--> calculateInterest");
        System.out.println("-> photo1="+photo1.toString());
        System.out.println("-> photo2="+photo2.toString());

        Set<String> photo1Tags = photo1.getTags();
        Set<String> photo2Tags = photo2.getTags();

        int common = 0;
        int photo1Only = 0;
        int photo2Only = 0;

        boolean intCurrentPhoto1Uniq = true;
        boolean intCurrentPhoto2Uniq = true;

        for(String tag1 : photo1Tags) {
            intCurrentPhoto1Uniq = true;

            for(String tag2 : photo2Tags) {
                if(tag1.equals(tag2)) {
                    common++;
                    intCurrentPhoto1Uniq = false;
                    break;
                }
            }
            if(intCurrentPhoto1Uniq) {
                photo1Only++;
            }
        }

        photo2Only = photo2Tags.size() - common;

        System.out.println("-> common="+common);
        System.out.println("-> photo1Only="+photo1Only);
        System.out.println("-> photo2Only="+photo2Only);

        int interest = min(common, photo1Only, photo2Only);

        System.out.println("-> interest="+interest);

        return interest;
    }

    public static int min(int a, int b, int c) {
        if (a <= b && a <= c) return a;
        if (b <= a && b <= c) return b;
        if (c <= a && c <= b) return c;
        throw new AssertionError("No value is smallest, how did that happen?");
    }
}
