package com.example.bookDownload.demos.web.util;

import com.example.bookDownload.demos.web.Entity.Download;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovelDownload {

    public Map<String, Object> novelDownload (Download download) throws Exception {

        if (download.getNovelUrl().contains("&")) {
            download.setNovelUrl(download.getNovelUrl().replace("&", "\"&\""));
        }

        if (download.getNovelDownloadUrl().contains("&")) {
            download.setNovelDownloadUrl(download.getNovelDownloadUrl().replace("&", "\"&\""));
        }

//        System.out.println(download.getNovelName());

        String[] arguments = new String[] {"python", new File("").getCanonicalPath() + "\\src\\main\\java\\com\\example\\bookDownload\\demos\\web\\util\\novelDownload.py", download.getUserName(), download.getPassWord(), download.getNovelUrl(), download.getNovelDownloadUrl()};
        for (int i = 0; i < arguments.length; i++) {
            System.out.println(arguments[i]);
        }
        Process process = Runtime.getRuntime().exec(arguments);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
        BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream(), "gbk"));
        String line;

        List<String> res = new ArrayList<String>();
        List<String> errMsg = new ArrayList<String>();

        while ((line = reader.readLine()) != null) {
            res.add(line);
        }
        reader.close();

        while ((line =err.readLine()) != null) {
            errMsg.add(line);
        }
        err.close();

        Map<String, Object> mp = new HashMap<>();
        mp.put("content", res);
        mp.put("error", errMsg);

        return mp;
    }
}
