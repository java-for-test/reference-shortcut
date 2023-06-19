package com.example.demo.service;

import com.example.demo.domain.ShortLink;
import com.example.demo.domain.ShortLinkVisit;
import com.example.demo.repository.ShortLinkRepository;
import com.example.demo.repository.ShortLinkVisitRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {

    @Autowired
    private ShortLinkVisitRepostory visitRepository;
    public void increaseStatistic(ShortLink shortLink, String userAgent){
        ShortLinkVisit visit = new ShortLinkVisit();
        visit.setShortLink(shortLink);
        visit.setUserAgent(userAgent);

        visitRepository.save(visit);
    }

    public Map<String, Long> collectUserAgentStats (ShortLink shortLink){
        List<ShortLinkVisit> history = visitRepository.findByShortLink(shortLink);
        Map<String, Long> map = new HashMap<>();

        history.forEach(vh ->{
            String userAgent = vh.getUserAgent();
            boolean contain = map.containsKey(userAgent);

            if (contain){
                Long stat = map.get(userAgent);
                map.put(userAgent, stat + 1);
            } else {
                map.put(userAgent, 1L);
            }
        });
        return map;
    }
}
