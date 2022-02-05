package com.example.droneAPIservice.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

public interface ImageService {

    public byte[] processImage(MultipartFile imageFile) throws IOException;

    public byte[] decompress(byte[] data) throws IOException, DataFormatException;

}