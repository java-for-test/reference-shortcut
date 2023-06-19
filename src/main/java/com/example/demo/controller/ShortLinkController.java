package com.example.demo.controller;

import com.example.demo.domain.ShortLink;
import com.example.demo.dto.ShortLinkDto;
import com.example.demo.dto.User;
import com.example.demo.service.ShortLinkService;
import com.example.demo.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/s")
public class ShortLinkController {

    @Autowired
    private ShortLinkService shortLinkService;

    @Autowired
    private StatisticService statisticService;

    @PostMapping
    public ResponseEntity create(@RequestBody ShortLinkDto dto) {
        return ResponseEntity.ok(shortLinkService.create(dto));
    }

    @GetMapping("/{code}")
    public ResponseEntity findRedirectLink(@PathVariable String code, @RequestHeader("User-Agent") String userAgent) {
        String redirectLink = shortLinkService.findRedirectLink(code, userAgent);
        return ResponseEntity.status(302).header("Location", redirectLink).build();
    }

    @GetMapping("/{code}/stat")
    public ResponseEntity getStats(@PathVariable String code){
        ShortLink shortLink = shortLinkService.findByCode(code);
        return ResponseEntity.ok(statisticService.collectUserAgentStats(shortLink));
    }
}
