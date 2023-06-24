package com.example.bookDownload.demos.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileUtil {

    // 读取当前文件内容
    public String fileContent (String path) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-16")));
        StringBuilder sb = new StringBuilder();
        String str;
        while((str = br.readLine()) != null){
            sb.append(str);
            sb.append('\n');
        }
        return sb.toString();
    }

    // 读取当前路径的所有文件名
    public List<String> fileFindAll (String path) {

        File file = new File(path);
        File[] files = file.listFiles();
        List<String> list = new ArrayList<>();
        for (File f: files) {
            list.add(f.getName());
        }

        return list;
    }

    // 将 json 文件转换为 map
    public HashMap<String, Object> jsonReader (String path) throws Exception {

        InputStream inputStream = new FileInputStream(path);
        HashMap<String, Object> map = new ObjectMapper().readValue(inputStream, HashMap.class);
        return map;
    }

    public byte[] pictureReader (String path) throws Exception {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = ImageIO.read(new File(path));
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void main(String[] args) {

    }


}
