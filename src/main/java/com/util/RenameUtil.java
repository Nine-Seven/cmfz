package com.util;

import java.util.UUID;

public class RenameUtil {

    public static String rename(String picFileName) {
        String uuid = UUID.randomUUID().toString();
        String subFile = picFileName.substring(picFileName.lastIndexOf('.'));
        return uuid + subFile;
    }
}
