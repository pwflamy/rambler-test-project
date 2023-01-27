package ru.pwflamy.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class PriceGreaterFilterHandlerTest {

    private static final Filter FILTER = Filter.priceGreater("50");

    private final PriceGreaterFilterHandler handler = new PriceGreaterFilterHandler();

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testIsMatch(Banner banner, Filter filter, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, handler.isMatch(banner, filter));
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(new Banner(), new Filter(), false),
                Arguments.of(new Banner().setPrice(new BigDecimal("10")), FILTER, false),
                Arguments.of(new Banner().setPrice(new BigDecimal("50")), FILTER, false),
                Arguments.of(new Banner().setPrice(new BigDecimal("60")), FILTER, true)
        );
    }
}