package com.example.demo.repository;

import com.example.demo.domain.ShortLink;
import com.example.demo.domain.ShortLinkVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortLinkVisitRepostory extends JpaRepository<ShortLinkVisit, Long> {
    List<ShortLinkVisit> findByShortLink(ShortLink shortLink);
}
