package ru.pwflamy.service;

import org.springframework.stereotype.Component;
import ru.pwflamy.dto.Banner;
import ru.pwflamy.dto.Filter;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuctionService {

    private final FilterService filterService;
    private final BannersSelectorService bannersSelectorService;

    public AuctionService(FilterService filterService,
                          BannersSelectorService bannersSelectorService) {
        this.filterService = filterService;
        this.bannersSelectorService = bannersSelectorService;
    }

    public List<Banner> findActualBanners(List<Banner> rawBanners, int amount, List<Filter> filters) {
        List<Banner> filteredBanners = rawBanners.stream()
                .filter(banner -> filterService.isMatch(banner, filters))
                .collect(Collectors.toList());
        return bannersSelectorService.selectBanners(filteredBanners, amount);
    }
}
