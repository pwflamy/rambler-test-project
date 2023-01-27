package ru.pwflamy.filter;

import org.springframework.stereotype.Component;
import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;
import ru.pwflamy.dto.FilterType;

import java.math.BigDecimal;

@Component
public class PriceGreaterFilterHandler implements FilterHandler {

    @Override
    public boolean isMatch(Banner banner, Filter filter) {
        if (banner.getPrice() == null) {
            return false;
        }
        return banner.getPrice().compareTo(new BigDecimal(filter.getValue())) > 0;
    }

    @Override
    public FilterType filterType() {
        return FilterType.PRICE_GREATER;
    }
}
