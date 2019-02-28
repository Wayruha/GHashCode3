package edu.hashcode;

import java.util.ArrayList;
import java.util.List;

public class UnionTwoTags {


    public Slide unionPlease(List<Photo> left, List<Photo> right, String leftTag, String rightTag) throws Exception {
        //find vertical photo to combine
        int leftVerticalCount = 0;
        int rightVerticalCount = 0;
        for (Photo phL : left) {
            if (!phL.horizontal) leftVerticalCount++;
        }
        for (Photo phR : left) {
            if (!phR.horizontal) leftVerticalCount++;
        }

        if (leftVerticalCount % 2 == 1 && rightVerticalCount % 2 == 1) {
            //TODO WHICH ONEs???
            //new Slide()
            return null;
        } else {
            //Need to find intersection photo.
            return new Slide(findJoinCandidate(left, leftTag, rightTag));
        }
    }

    private Photo findJoinCandidate(List<Photo> left, String leftTag, String rightTag) throws Exception {
        List<Photo> joinCandidates = new ArrayList<>();
        for (Photo lPh : left) {
            if (lPh.getTags().contains(rightTag)) {
                joinCandidates.add(lPh);
            }
        }
        if (joinCandidates.isEmpty()) {
            System.out.println("No intersection btw " + leftTag + " and " + rightTag);
            throw new Exception("No intersection!");
        }

        Photo bestCandidate = joinCandidates.get(0);
        int candidateTagCount = bestCandidate.tags.size();
        for (Photo ph : joinCandidates) {
            if (ph.tags.size() < candidateTagCount) {
                candidateTagCount = ph.tags.size();
                bestCandidate = ph;
            }
        }
        return bestCandidate;

    }

}
