package com.example.bookDownload.demos.web.Mapper;

import com.example.bookDownload.demos.web.Entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NovelMapper {

    List<Novel> queryAll();

    List<Novel> selByName(String title);

    int selByNameExist(String title);

    int insNovel(Novel novel);
}
