package ru.pwflamy.filter;

import org.springframework.stereotype.Component;
import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;
import ru.pwflamy.dto.FilterType;

@Component
public class CountryFilterHandler implements FilterHandler {

    @Override
    public boolean isMatch(Banner banner, Filter filter) {
        if (banner.getCountries() == null || banner.getCountries().isEmpty()) {
            return true;
        }
        return banner.getCountries().contains(filter.getValue());
    }

    @Override
    public FilterType filterType() {
        return FilterType.COUNTRY;
    }
}
