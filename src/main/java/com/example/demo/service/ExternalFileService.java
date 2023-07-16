package com.example.demo.service;

import com.example.demo.domain.ExternalFile;
import com.example.demo.domain.ShortLink;
import com.example.demo.repository.ExternalFileRepository;
import com.example.demo.repository.ShortLinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ExternalFileService {

    @Autowired
    private ExternalFileRepository exFile;

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    public ExternalFile createExFile(byte[] qrName, ShortLink shortLink){
        return exFile.save(new ExternalFile(qrName, shortLink));
    }

    public ExternalFile findExFile(ShortLink shortLink){
        return exFile.findExternalFileByShortLink(shortLink);
    }
}
