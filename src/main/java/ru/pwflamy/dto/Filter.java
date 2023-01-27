package ru.pwflamy.dto;

import java.util.Objects;

public class Filter {

    private FilterType type;
    private String value;

    public Filter() {
    }

    public Filter(FilterType type, String value) {
        this.type = type;
        this.value = value;
    }

    public FilterType getType() {
        return type;
    }

    public Filter setType(FilterType type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Filter setValue(String value) {
        this.value = value;
        return this;
    }

    public static Filter country(String country) {
        return new Filter(FilterType.COUNTRY, country);
    }

    public static Filter priceGreater(String value) {
        return new Filter(FilterType.PRICE_GREATER, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return type == filter.type &&
                Objects.equals(value, filter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "Filter{" +
                "type=" + type +
                ", expectedValue='" + value + '\'' +
                '}';
    }
}
