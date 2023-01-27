package ru.pwflamy.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Banner {

    private Long id;
    private BigDecimal price;
    private String companyId;
    private Set<String> countries;
    private boolean special;

    public Banner() {
    }

    public Banner(Long id, BigDecimal price, String companyId, Set<String> countries) {
        this.id = id;
        this.price = price;
        this.companyId = companyId;
        this.countries = countries;
    }

    public Banner(Long id, BigDecimal price, String companyId, Set<String> countries, boolean special) {
        this(id, price, companyId, countries);
        this.special = special;
    }

    public Long getId() {
        return id;
    }

    public Banner setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Banner setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public Banner setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public Set<String> getCountries() {
        return countries;
    }

    public Banner setCountries(Set<String> countries) {
        this.countries = countries;
        return this;
    }

    public boolean getSpecial() {
        return special;
    }

    public Banner setSpecial(boolean special) {
        this.special = special;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return special == banner.special &&
                Objects.equals(id, banner.id) &&
                Objects.equals(price, banner.price) &&
                Objects.equals(companyId, banner.companyId) &&
                Objects.equals(countries, banner.countries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, companyId, countries, special);
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", price=" + price +
                ", companyId='" + companyId + '\'' +
                ", countries=" + countries +
                ", special=" + special +
                '}';
    }
}
