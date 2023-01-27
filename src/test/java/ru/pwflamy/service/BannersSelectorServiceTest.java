package ru.pwflamy.service;

import org.assertj.core.api.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.pwflamy.dto.Banner;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

public class BannersSelectorServiceTest {


    private static final Banner BANNER_1 = new Banner(1L, new BigDecimal("20"), "comp1", new HashSet<>());
    private static final Banner BANNER_2 = new Banner(2L, new BigDecimal("30"), "comp1", new HashSet<>());
    private static final Banner BANNER_3 = new Banner(3L, new BigDecimal("40"), "comp2", new HashSet<>());
    private static final Banner BANNER_4 = new Banner(4L, new BigDecimal("40"), "comp2", new HashSet<>());
    private static final Banner BANNER_5 = new Banner(4L, new BigDecimal("30"), "comp2", new HashSet<>());

    private final BannersSelectorService bannersSelectorService = new BannersSelectorService();

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testSelectBanners(List<Banner> banners, int amount, List<Banner> exactlyContains, List<Banner> possibleContains, int expectedSize) {
        List<Banner> result = bannersSelectorService.selectBanners(banners, amount);
        assertThat(result)
                .hasSize(expectedSize)
                .has(anyOf(
                        new Condition<>(list -> exactlyContains.isEmpty(), "no exactly banners"),
                        new Condition<>(list -> list.containsAll(exactlyContains), "contains all from exactlyContains")
                ))
                .has(anyOf(
                        new Condition<>(list -> possibleContains.isEmpty(), "no possible banners"),
                        new Condition<>(list -> list.stream().anyMatch(possibleContains::contains), "contains at least 1 from possible banners")
                ));
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(Arrays.asList(BANNER_1, BANNER_2), 0, emptyList(), emptyList(), 0),
                Arguments.of(Arrays.asList(BANNER_1, BANNER_2, BANNER_3), 2, List.of(BANNER_2, BANNER_3), emptyList(), 2),
                Arguments.of(Arrays.asList(BANNER_1, BANNER_2, BANNER_3, BANNER_4), 2, List.of(BANNER_2), List.of(BANNER_4, BANNER_3), 2),
                Arguments.of(Arrays.asList(BANNER_3, BANNER_4, BANNER_5), 2, emptyList(), List.of(BANNER_4, BANNER_3), 1)
        );
    }
}