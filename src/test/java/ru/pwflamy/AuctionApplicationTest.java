package ru.pwflamy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;
import ru.pwflamy.service.AuctionService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Тестовый класс, который осуществляет поднятие контекста и позволяет проверить работу приложения в "боевом" формате.
 */
@Configuration
@ComponentScan("ru.pwflamy")
@ContextConfiguration(classes = AuctionApplicationTest.class)
@ExtendWith(SpringExtension.class)
public class AuctionApplicationTest {

    private static final Banner BANNER_1 = new Banner(1L, new BigDecimal("20"), "comp1", new HashSet<>());
    private static final Banner BANNER_2 = new Banner(2L, new BigDecimal("20"), "comp1", Set.of("RUSSIA", "FRANCE"));
    private static final Banner BANNER_3 = new Banner(3L, new BigDecimal("40"), "comp2", Set.of("RUSSIA"));
    private static final Banner BANNER_4 = new Banner(4L, new BigDecimal("30"), "comp2", Set.of("RUSSIA", "FRANCE"));
    private static final Banner BANNER_5 = new Banner(5L, new BigDecimal("10"), "comp1", Set.of("FRANCE"));
    private static final Banner BANNER_6 = new Banner(6L, new BigDecimal("30"), "comp3", Set.of("ITALY"));

    @Autowired
    private AuctionService auctionService;

    @Test
    public void testAllDifferentPrices() {
        List<Banner> rawBanners = List.of(BANNER_2, BANNER_3, BANNER_4, BANNER_5);
        List<Banner> result = auctionService.findActualBanners(rawBanners, 2, List.of(Filter.country("RUSSIA")));
        Assertions.assertThat(result)
                .containsExactlyInAnyOrder(BANNER_3, BANNER_2);
    }

    @Test
    public void testHasSamePrice() {
        List<Banner> rawBanners = List.of(BANNER_1, BANNER_2, BANNER_3, BANNER_4, BANNER_5);
        List<Banner> result = auctionService.findActualBanners(rawBanners, 2, List.of(Filter.country("RUSSIA")));
        Assertions.assertThat(result)
                .hasSize(2)
                .contains(BANNER_3)
                .containsAnyOf(BANNER_1, BANNER_2);
    }

    @Test
    public void testNoCountryMatching() {
        List<Banner> rawBanners = List.of(BANNER_5, BANNER_6);
        List<Banner> result = auctionService.findActualBanners(rawBanners, 2, List.of(Filter.country("RUSSIA")));
        Assertions.assertThat(result)
                .isEmpty();
    }

    @Test
    public void testMatchEmptyCountry() {
        List<Banner> rawBanners = List.of(BANNER_1, BANNER_3, BANNER_4);
        List<Banner> result = auctionService.findActualBanners(rawBanners, 3, List.of(Filter.country("RUSSIA")));
        Assertions.assertThat(result)
                .containsExactlyInAnyOrder(BANNER_3, BANNER_1);
    }

    @Test
    public void testNotEnoughBanner() {
        List<Banner> rawBanners = List.of(BANNER_4, BANNER_5, BANNER_6);
        List<Banner> result = auctionService.findActualBanners(rawBanners, 2, List.of(Filter.country("ITALY")));
        Assertions.assertThat(result)
                .containsExactlyInAnyOrder(BANNER_6);
    }

    @Test
    public void testPriceFilter() {
        List<Banner> rawBanners = List.of(BANNER_1, BANNER_2, BANNER_3, BANNER_4, BANNER_5);
        List<Banner> result = auctionService.findActualBanners(rawBanners, 2, List.of(Filter.country("RUSSIA"), Filter.priceGreater("25")));
        Assertions.assertThat(result)
                .containsExactlyInAnyOrder(BANNER_3);
    }
}