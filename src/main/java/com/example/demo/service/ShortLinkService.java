package com.example.demo.service;

import com.example.demo.domain.ShortLink;
import com.example.demo.dto.ShortLinkDto;
import com.example.demo.repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkService {

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    @Autowired
    private StatisticService statisticService;

    public ShortLinkDto create(ShortLinkDto dto){
        if (dto.getCode() == null){
            GenerateShortLink generatedString = new GenerateShortLink();
            ShortLink entity = shortLinkRepository.save(new ShortLink(generatedString.Generate().substring(0, 8), dto.getFullLink()));
            return new ShortLinkDto(entity.getId(), entity.getCode(), entity.getFullLink());
        } else {
            ShortLink entity = shortLinkRepository.save(new ShortLink(dto.getCode(), dto.getFullLink()));
            return new ShortLinkDto(entity.getId(), entity.getCode(), entity.getFullLink());
        }
//        return new ShortLinkDto(entity.getId(), entity.getCode(), entity.getFullLink());
    }

    public String findRedirectLink(String code, String userAgent){
        ShortLink shortLink = shortLinkRepository.findShortLinkByCode(code);
        statisticService.increaseStatistic(shortLink, userAgent);
        return shortLink.getFullLink();
    }

    public ShortLink findByCode(String code){
        ShortLink shortLink = shortLinkRepository.findShortLinkByCode(code);
        return shortLink;
    }
}
