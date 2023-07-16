package com.example.demo.repository;

import com.example.demo.domain.ExternalFile;
import com.example.demo.domain.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalFileRepository extends JpaRepository<ExternalFile, byte[]> {

    ExternalFile findExternalFileByQRCodeName(byte[] QRCodeName);

    ExternalFile findExternalFileByShortLink(ShortLink shortLink);
}
