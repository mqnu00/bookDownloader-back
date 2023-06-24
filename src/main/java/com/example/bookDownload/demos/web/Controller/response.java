package com.example.bookDownload.demos.web.Controller;

import com.example.bookDownload.demos.web.Entity.*;
import com.example.bookDownload.demos.web.Mapper.NovelMapper;
import com.example.bookDownload.demos.web.Mapper.VolumeMapper;
import com.example.bookDownload.demos.web.util.FileUtil;
import com.example.bookDownload.demos.web.util.NovelDownload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class response {

    @Autowired
    private VolumeMapper volumeMapper;

    @Autowired
    private NovelMapper novelMapper;

    @PostMapping ("/test")
    public List<Volume> test (
        @RequestBody Map<String, Object> res
    ) {
        return null;
    }

    @GetMapping("/test")
    public String res () {
        return "123";
    }

    @GetMapping("/novel/page/load")
    public List<NovelWithBLOBs> novelListLoad() {

        NovelExample novelExample = new NovelExample();
        novelExample.createCriteria();
        return novelMapper.selectByExampleWithBLOBs(novelExample);
    }

    // 加载小说卷数据
    @GetMapping("/volumeList")
    public List<Volume> volumeListLoad (
            @RequestParam("novel_id") int novel_id
            ) {

        VolumeExample volumeExample = new VolumeExample();
        VolumeExample.Criteria criteria = volumeExample.createCriteria();
        criteria.andNovelIdEqualTo(novel_id);
        return volumeMapper.selectByExample(volumeExample);
    }

    //小说卷内容
    @GetMapping("/volumeReader")
    public List<Volume> volumeReader (
            @RequestParam("volume_id") int volume_id
    ) {

        List<Volume> res = new ArrayList<>();
        res.add(volumeMapper.selectByPrimaryKey(volume_id));
        return res;
    }

//    下载小说
    @PostMapping ("/download")
    public Map<String, Object> novelDownload (@RequestBody Download download) {
        try {
            return new NovelDownload().novelDownload(download);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> mp = new HashMap<>();
            mp.put("content", "null");
            mp.put("error", e.toString());
            return mp;
        }
    }

//    更新小说数据库
    @GetMapping("/update")
    public Map<String, Object> novelInsert () {

        Map<String, Object> mp = new HashMap<>();
        mp.put("content", "");
        mp.put("error", "null");

        try {

            String novelPath = new File("").getCanonicalPath() + "\\src\\main\\java\\com\\example\\bookDownload\\novel";
            List<String> novelToVolume = new FileUtil().fileFindAll(novelPath);

            // 枚举小说
            for (String novelName : novelToVolume) {

                // 根据小说的信息生成实体
                HashMap<String, Object> map = new FileUtil().jsonReader(novelPath + "\\" + novelName + "\\info.json");
                NovelWithBLOBs novel = new NovelWithBLOBs(
                        new ObjectMapper().convertValue(map.get("novelName"), String.class),
                        new ObjectMapper().convertValue(map.get("author"), String.class),
                        new ObjectMapper().convertValue(map.get("description"), String.class),
                        new FileUtil().pictureReader(novelPath + "\\" + novelName + "\\" + novelName + ".jpg")
                );
                // 如果这个小说没有在表中
                NovelExample novelExample = new NovelExample();
                NovelExample.Criteria novelC = novelExample.createCriteria();
                novelC.andTitleEqualTo(novelName);
                if (novelMapper.selectByExample(novelExample).size() == 0) {

                    try {

                        novelMapper.insert(novel);
                        mp.put("content", new ObjectMapper().convertValue(mp.get("content"), String.class)  + "\n" + novelName + " 添加成功");
                    } catch (Exception e) {
                        mp.put("error", mp.get("error") + "\n" + novelName + " 添加失败");
                    }
                } else mp.put("content", new ObjectMapper().convertValue(mp.get("content"), String.class) + "\n" + novelName + " 已存在");

                // 添加小说id
                novel.setId(novelMapper.selectByExample(novelExample).get(0).getId());

                // 添加小说卷
                List<String> volumes = new FileUtil().fileFindAll(novelPath + "\\" + novelName);


                for (String volume: volumes) {

                    if (volume.contains("jpg")) continue;
                    if (volume.contains("json")) continue;

                    VolumeExample volumeExample = new VolumeExample();
                    VolumeExample.Criteria volumeC = volumeExample.createCriteria();
                    volumeC.andNovelIdEqualTo(novel.getId());
                    volumeC.andTitleEqualTo(volume.replace(".txt", ""));

                    // 小说不存在这一卷
                    if (volumeMapper.selectByExample(volumeExample).size() == 0) {
                        try {
                            volumeMapper.insert(new Volume(
                                    volume.replace(".txt", ""),
                                    novel.getId(),
                                    new FileUtil().fileContent(novelPath + "\\" + novelName + "\\" + volume)
                            ));
                            mp.put("content", new ObjectMapper().convertValue(mp.get("content"), String.class)  + "\n" + novelName + " " + volume + " 添加成功");
                        } catch (Exception e) {
                            mp.put("error", mp.get("error") + "\n" + novelName + " " + volume + " 添加失败");
                        }
                    }

                }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mp;
    }
}
