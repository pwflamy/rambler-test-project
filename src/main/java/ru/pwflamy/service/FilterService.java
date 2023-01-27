package ru.pwflamy.service;

import org.springframework.stereotype.Component;
import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;
import ru.pwflamy.filter.FilterHandler;
import ru.pwflamy.dto.FilterType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FilterService {

    private final Map<FilterType, FilterHandler> filterHandlers;

    public FilterService(List<FilterHandler> filterHandlers) {
        this.filterHandlers = filterHandlers.stream().collect(Collectors.toMap(FilterHandler::filterType, Function.identity()));
    }

    public boolean isMatch(Banner banner, List<Filter> filters) {
        return filters.stream().allMatch(filter -> isMatch(banner, filter));
    }

    private boolean isMatch(Banner banner, Filter filter) {
        FilterHandler filterHandler = filterHandlers.get(filter.getType());
        return filterHandler.isMatch(banner, filter);
    }
}
