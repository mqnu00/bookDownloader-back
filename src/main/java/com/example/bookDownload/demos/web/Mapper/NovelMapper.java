package com.example.bookDownload.demos.web.Mapper;

import com.example.bookDownload.demos.web.Entity.Novel;
import com.example.bookDownload.demos.web.Entity.NovelExample;
import com.example.bookDownload.demos.web.Entity.NovelWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NovelMapper {
    long countByExample(NovelExample example);

    int deleteByExample(NovelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NovelWithBLOBs record);

    int insertSelective(NovelWithBLOBs record);

    List<NovelWithBLOBs> selectByExampleWithBLOBs(NovelExample example);

    List<Novel> selectByExample(NovelExample example);

    NovelWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NovelWithBLOBs record, @Param("example") NovelExample example);

    int updateByExampleWithBLOBs(@Param("record") NovelWithBLOBs record, @Param("example") NovelExample example);

    int updateByExample(@Param("record") Novel record, @Param("example") NovelExample example);

    int updateByPrimaryKeySelective(NovelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NovelWithBLOBs record);

    int updateByPrimaryKey(Novel record);
}