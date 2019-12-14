package com.sparknetworks.personalitytest.domain.question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize
public class Range<T extends Number> {

    private T from;
    private T to;

    private Range() {
    }

    public Range(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Range)) return false;
        Range<?> range = (Range<?>) o;
        return Objects.equals(from, range.from) &&
                Objects.equals(to, range.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
