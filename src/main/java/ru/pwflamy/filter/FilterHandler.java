package ru.pwflamy.filter;

import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;
import ru.pwflamy.dto.FilterType;

public interface FilterHandler {

    boolean isMatch(Banner banner, Filter filter);

    FilterType filterType();
}
