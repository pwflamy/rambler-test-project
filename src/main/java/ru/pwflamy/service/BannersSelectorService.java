package ru.pwflamy.service;

import org.springframework.stereotype.Component;
import ru.pwflamy.dto.Banner;

import java.util.*;

@Component
public class BannersSelectorService {

    public List<Banner> selectBanners(List<Banner> banners, int amount) {
        // выполняем shuffle, чтобы баннеры с одинаковой ценой равновероятно встречались в выводе
        Collections.shuffle(banners);
        banners.sort(Comparator.comparing(Banner::getPrice, Comparator.reverseOrder()));
        Set<String> companyIds = new HashSet<>();
        List<Banner> result = new ArrayList<>();
        for (Banner banner : banners) {
            if (companyIds.size() == amount) {
                break;
            }
            if (!companyIds.contains(banner.getCompanyId())) {
                result.add(banner);
                companyIds.add(banner.getCompanyId());
            }
        }
        return result;
    }
}
