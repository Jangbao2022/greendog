package com.boob.greendog.util;

import com.boob.greendog.GreendogApplication;

import java.io.*;
import java.net.URL;
import java.util.UUID;

public class FileUploadUtil {

    /**
     * 上传文件
     *
     * @param is   输入文件流
     * @param path 存储的位置
     * @return URL 返回一个url
     */
    public static String upLoadFile(InputStream is, String path, String suffix) {

        //设置文件名
        String filename = UUID.randomUUID().toString() + "." + suffix;
        try {

            //设置文件存储的路径
            String resource = GreendogApplication.class
                    .getClassLoader()
                    .getResource("./static/" + path + "/")
                    .getPath() + filename;

            OutputStream os = new FileOutputStream(resource);
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回URL
        return "/" + path + "/" + filename;
    }
}
