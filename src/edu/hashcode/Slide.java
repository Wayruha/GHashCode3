package edu.hashcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public String toString() {
        return "{\"left\":" + (left == null ? "null" : "\"" + left + "\"") + (right == null ? "" : ", " + "\"right\":" + ("\"" + right + "\"") + ", ") + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slide slide = (Slide) o;
        return Objects.equals(left, slide.left) &&
                Objects.equals(right, slide.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
