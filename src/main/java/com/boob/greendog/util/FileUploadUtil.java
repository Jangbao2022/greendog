package com.boob.greendog.util;

import com.boob.greendog.GreendogApplication;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class FileUploadUtil {

    /**
     * 上传文件通过文件名
     *
     * @param is   输入文件流
     * @param path 存储的位置
     * @param name 文件名
     * @return URL 返回一个url
     */
    public static String upLoadFileByName(InputStream is, String path, String name) {
        //获取文件后缀
        String[] split = name.split("\\.");
        String suffix = split[1];
        return upLoadFileBySuffix(is, path, suffix);
    }

    /**
     * 上传文件通过MultipartFile
     *
     * @param file 输入文件
     * @param path 存储的位置
     * @return URL 返回一个url
     */
    public static String upLoadFileByMultipartFile(MultipartFile file, String path) {
        try {
            return upLoadFileByName(file.getInputStream(), path, file.getResource().getFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传文件
     *
     * @param is     输入文件流
     * @param path   存储的位置
     * @param suffix 文件后缀
     * @return URL 返回一个url
     */
    private static String upLoadFileBySuffix(InputStream is, String path, String suffix) {
        OutputStream os = null;
        //设置文件名
        String filename = UUID.randomUUID().toString() + "." + suffix;
        try {

            //设置文件存储的路径
            String resource = GreendogApplication.class
                    .getClassLoader()
                    .getResource("./static/" + path + "/")
                    .getPath() + filename;

            os = new FileOutputStream(resource);
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //返回URL
        return "/" + path + "/" + filename;
    }
}
