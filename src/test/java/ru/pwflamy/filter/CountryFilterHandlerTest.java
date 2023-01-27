package ru.pwflamy.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CountryFilterHandlerTest {

    private static final Filter FILTER = Filter.country("RUSSIA");

    private final CountryFilterHandler handler = new CountryFilterHandler();

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testIsMatch(Banner banner, Filter filter, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, handler.isMatch(banner, filter));
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(new Banner(), new Filter(), true),
                Arguments.of(new Banner().setCountries(new HashSet<>()), FILTER, true),
                Arguments.of(new Banner().setCountries(new HashSet<>()), FILTER, true),
                Arguments.of(new Banner().setCountries(Set.of("FRANCE")), FILTER, false),
                Arguments.of(new Banner().setCountries(Set.of("RUSSIA")), FILTER, true)
        );
    }
}