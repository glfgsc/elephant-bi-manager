package com.elephant.elephant_bi.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    /**
     * 创建文件
     * @param path
     * @throws IOException
     */
    public static void createFile(String directory,String fileName)  {
        try{
            directory = new ClassPathResource(".").getFile().getPath() + directory;
            File dir = new File(directory);
            if(!dir.exists()){
                dir.mkdirs();
            }
            String filePath = directory + "/" + fileName;
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除文件
     * @param path
     */
    public static void deleteFile(String path){
        try{
            String filePath = new ClassPathResource(".").getFile().getPath() + path;
            File file = new File(filePath);
            file.delete();
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}
