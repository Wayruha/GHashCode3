package edu.hashcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UnionTwoTags {

    Map<String, List<Slide>> stringListMap;
    Map<String, List<Photo>> leftAloneVerticalPhotosMap;

    public UnionTwoTags(Map<String, List<Slide>> stringListMap, Map<String, List<Photo>> leftAloneVerticalPhotosMap) {
        this.stringListMap = stringListMap;
        this.leftAloneVerticalPhotosMap = leftAloneVerticalPhotosMap;
    }

    public List<String> groupTags(List<String> tagsByPopularity) {
        for (int i = 0; i < tagsByPopularity.size() - 1; i++) {
            for (int j = i + 1; j < tagsByPopularity.size(); j++) {
                if (tryToUnion(tagsByPopularity.get(i), tagsByPopularity.get(j))) {
                    Collections.swap(tagsByPopularity, i + 1, j);
                    break;
                }

            }
        }
        return tagsByPopularity;
    }

    public boolean tryToUnion(String tagL, String tagR) {

        try {
            return unionPlease(tagL, tagR) != null;
        } catch (CantUnionException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public Slide unionPlease(String tagLeft, String tagRight) throws CantUnionException {
        final List<Slide> left = stringListMap.get(tagLeft);
        final List<Slide> right = stringListMap.get(tagRight);
        //find vertical photo to combine

        final List<Photo> leftAlonePhotos = leftAloneVerticalPhotosMap.get(tagLeft);
        final List<Photo> rightAlonePhotos = leftAloneVerticalPhotosMap.get(tagRight);
        if (!leftAlonePhotos.isEmpty() && !rightAlonePhotos.isEmpty()) {
            return new Slide(leftAlonePhotos.get(0), rightAlonePhotos.get(0));
        } else {
            //Need to find intersection photo.
            return findJoinCandidate(left, right, tagLeft, tagRight);
        }
    }

    private Slide findJoinCandidate(List<Slide> left, List<Slide> right, String leftTag, String rightTag) throws CantUnionException {
        List<Slide> joinCandidates = new ArrayList<>();
        for (Slide lPh : left) {
            for (Slide rPh : right) {
                if (!Collections.disjoint(lPh.getTags(), rPh.getTags())) {
                    joinCandidates.add(lPh);
                }
            }
        }
        if (joinCandidates.isEmpty()) {
            System.out.println("No intersection btw " + leftTag + " and " + rightTag);
            throw new CantUnionException("No intersection!");
        }

        Slide bestCandidate = joinCandidates.get(0);
        int candidateTagCount = bestCandidate.getTags().size();
        for (Slide ph : joinCandidates) {
            if (ph.getTags().size() < candidateTagCount) {
                candidateTagCount = ph.getTags().size();
                bestCandidate = ph;
            }
        }
        return bestCandidate;

    }

}
