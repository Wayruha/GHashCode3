package edu.hashcode;

import java.util.HashSet;
import java.util.Set;

public class Slide {

    private Photo left;
    private Photo right;
    private Set<String> tags = new HashSet<>();

    public Slide(Photo photo) {
        left = photo;
        tags.addAll(photo.getTags());
    }

    public Slide(Photo photo1, Photo photo2) {
        left = photo1;
        right = photo2;
        tags.addAll(photo1.getTags());
        tags.addAll(photo2.getTags());
    }

    public Set<String> getTags() {
        return tags;
    }

    public Photo getLeft() {
        return left;
    }

    public Photo getRight() {
        return right == null ? left : right;
    }

}
