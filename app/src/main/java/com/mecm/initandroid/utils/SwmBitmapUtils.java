package com.mecm.initandroid.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Bitmap 转 byte
 */
public class SwmBitmapUtils {

    /*
    * Bitmap → byte[]
    */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        if (bm == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

}
