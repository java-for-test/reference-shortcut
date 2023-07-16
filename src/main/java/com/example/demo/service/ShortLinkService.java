package com.example.demo.service;

import com.example.demo.domain.ExternalFile;
import com.example.demo.domain.ShortLink;
import com.example.demo.dto.ShortLinkDto;
import com.example.demo.repository.ExternalFileRepository;
import com.example.demo.repository.ShortLinkRepository;
import lombok.extern.slf4j.Slf4j;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ShortLinkService {

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private ExternalFileService externalFileService;

    @Autowired
    private ExternalFileRepository externalFileRepository;

//    @Value("${shortlink.prefix}")
//    private String urlPrefix;

    public ShortLinkDto create(ShortLinkDto dto){
        if (dto.getCode() == null){
            GenerateShortLink generatedString = new GenerateShortLink();
            ShortLink entity = shortLinkRepository.save(new ShortLink(generatedString.Generate().substring(0, 8), dto.getFullLink()));
            log.info("Succesfull generation shortlink with code [" + entity.getCode() + "] for link [" + entity.getFullLink() + "]");
            return new ShortLinkDto(entity.getId(), entity.getCode(), entity.getFullLink());
        } else {
            ShortLink entity = shortLinkRepository.save(new ShortLink(dto.getCode(), dto.getFullLink()));
            return new ShortLinkDto(entity.getId(), entity.getCode(), entity.getFullLink());
        }
    }

    public String findRedirectLink(String code, String userAgent){
        ShortLink shortLink = shortLinkRepository.findShortLinkByCode(code);
        statisticService.increaseStatistic(shortLink, userAgent);
        log.info("Redirected link was found");
        return shortLink.getFullLink();
    }

    public ShortLink findByCode(String code){
        ShortLink shortLink = shortLinkRepository.findShortLinkByCode(code);
        log.info("Looking up for link with code " + code);
        return shortLink;
    }

    @Transactional
    public byte[] generateQRCode(ShortLink shortLink){
        try {
            log.info("QRCode was found for link [" + shortLink + "]");
            ExternalFile externalFile = externalFileRepository.findExternalFileByShortLink(shortLink);
            return externalFile.getQRCodeName();
        } catch (NullPointerException e){
            System.out.println(e);
            log.info("QRCode was not in base, so we generate it");
            var  stream = QRCode.from("http://localhost/s/" +  shortLink.toString())
                    .to(ImageType.JPG)
                    .withSize(250, 250)
                    .stream();
            externalFileService.createExFile(stream.toByteArray(), shortLink);
            return stream.toByteArray();
        }
    }
}
