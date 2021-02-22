package com.laptrinhjavaweb.util;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class UploadFileUtil {

    private final String root = "C:\\Users\\LNc\\Documents\\Spring-Boot-GIT\\src\\main\\resources\\static\\admin\\assets\\images";

    public void saveFile(byte[] bytes, String path) {
        File file = new File(StringUtils.substringBeforeLast(root + path, "/"));
        if(!file.exists()){
            file.mkdirs();
            System.out.println(file.mkdirs());
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(new File(root + path))){
            fileOutputStream.write(bytes);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
