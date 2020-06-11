package com.example.mongodemo.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {
    public static byte[] imageBase642Byte(String imageBase64) {
        return Base64.decodeBase64(imageBase64);
    }

    public static String byte2Base64Str(byte[] imageByte) {
        return Base64.encodeBase64String(imageByte);
    }
}
