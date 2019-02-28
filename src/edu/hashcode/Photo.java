package edu.hashcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Photo implements Comparable {
    int id;
    boolean horizontal;
    Set<String> tags = new HashSet<>(); // use hashcodes

    public Photo() {
    }

    public Photo(int id, boolean horizontal) {
        this.id = id;
        this.horizontal = horizontal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id == photo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\"" + ", " +
                "\"horizontal\":\"" + horizontal + "\"" + ", " +
                "\"tags\":" + (tags == null ? "null" : Arrays.toString(tags.toArray())) +
                "}";
    }

    @Override
    public int compareTo(Object o) {
        Photo o1 = (Photo) o;
        return Integer.compare(this.tags.size(), o1.getTags().size());
    }
}
