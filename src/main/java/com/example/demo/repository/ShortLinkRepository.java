package com.example.demo.repository;

import com.example.demo.domain.ShortLink;
import com.example.demo.domain.ShortLinkVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    ShortLink findShortLinkByCode(String code);

//    List<ShortLinkVisit> findByShortLink(ShortLink shortLink);
}
