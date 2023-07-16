package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "external_file")
public class ExternalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name = "qrcode_name", unique = true)
    private byte[] QRCodeName;

//    @Column(name = "code_id")
    @OneToOne(targetEntity = ShortLink.class)
    private ShortLink shortLink;

    public ExternalFile(byte[] QRCodeName, ShortLink shortLink) {
        this.QRCodeName = QRCodeName;
        this.shortLink = shortLink;
    }

    public ExternalFile(byte[] QRCodeName) {
        this.QRCodeName = QRCodeName;
    }
}
