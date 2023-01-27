package ru.pwflamy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;
import ru.pwflamy.dto.FilterType;
import ru.pwflamy.filter.CountryFilterHandler;
import ru.pwflamy.filter.PriceGreaterFilterHandler;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {

    @Mock
    private CountryFilterHandler countryFilterHandler;
    @Mock
    private PriceGreaterFilterHandler priceGreaterFilterHandler;

    private FilterService filterService;

    @BeforeEach
    public void setUp() {
        when(countryFilterHandler.filterType()).thenReturn(FilterType.COUNTRY);
        when(priceGreaterFilterHandler.filterType()).thenReturn(FilterType.PRICE_GREATER);
        filterService = new FilterService(List.of(countryFilterHandler, priceGreaterFilterHandler));
    }

    @Test
    public void testIsMatch() {
        when(countryFilterHandler.isMatch(any(), any())).thenReturn(true);
        when(priceGreaterFilterHandler.isMatch(any(), any())).thenReturn(true);
        Assertions.assertTrue(filterService.isMatch(new Banner(), List.of(Filter.country("RUSSIA"), Filter.priceGreater("50"))));
        verify(countryFilterHandler, times(1)).isMatch(any(), any());
        verify(priceGreaterFilterHandler, times(1)).isMatch(any(), any());
    }
}