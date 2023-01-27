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

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {

    @Mock
    private CountryFilterHandler handler;

    private FilterService filterService;

    @BeforeEach
    public void setUp() {
        when(handler.filterType()).thenReturn(FilterType.COUNTRY);
        filterService = new FilterService(List.of(handler));
    }

    @Test
    public void testIsMatch() {
        when(handler.isMatch(any(), any())).thenReturn(true);
        Assertions.assertTrue(filterService.isMatch(new Banner(), List.of(new Filter().setType(FilterType.COUNTRY))));
    }
}