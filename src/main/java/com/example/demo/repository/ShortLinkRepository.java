package com.example.demo.repository;

import com.example.demo.domain.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    ShortLink findShortLinkByCode(String code);

}
