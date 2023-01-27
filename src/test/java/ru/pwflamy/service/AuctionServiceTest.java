package ru.pwflamy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.pwflamy.dto.Banner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuctionServiceTest {

    @Mock
    private FilterService filterService;
    @Mock
    private BannersSelectorService bannersSelectorService;

    @InjectMocks
    private AuctionService auctionService;

    @Test
    public void findActualBanners() {
        Banner banner1 = new Banner().setId(1L);
        Banner banner2 = new Banner().setId(2L);
        List<Banner> rawBanners = List.of(banner1, banner2);
        List<Banner> result = List.of(banner1);
        when(filterService.isMatch(eq(banner1), any())).thenReturn(true);
        when(filterService.isMatch(eq(banner2), any())).thenReturn(false);
        when(bannersSelectorService.selectBanners(result, 2)).thenReturn(result);

        Assertions.assertEquals(result, auctionService.findActualBanners(rawBanners, 2, new ArrayList<>()));
    }
}