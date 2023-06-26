package com.example.demo.service;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.ByteArrayOutputStream;

public class GenerateQRCode {

    private ByteArrayOutputStream stream;

    public byte[] GenerateQRCode(String code){
        stream = QRCode.from("http://localhost:8080/s/" + code).to(ImageType.JPG).withSize(250, 250).stream();
        return stream.toByteArray();
    }
}
