package com.example.bookDownload.demos.web.Mapper;

import com.example.bookDownload.demos.web.Entity.Volume;
import com.example.bookDownload.demos.web.Entity.VolumeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VolumeMapper {
    long countByExample(VolumeExample example);

    int deleteByExample(VolumeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Volume record);

    int insertSelective(Volume record);

    List<Volume> selectByExampleWithBLOBs(VolumeExample example);

    List<Volume> selectByExample(VolumeExample example);

    Volume selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Volume record, @Param("example") VolumeExample example);

    int updateByExampleWithBLOBs(@Param("record") Volume record, @Param("example") VolumeExample example);

    int updateByExample(@Param("record") Volume record, @Param("example") VolumeExample example);

    int updateByPrimaryKeySelective(Volume record);

    int updateByPrimaryKeyWithBLOBs(Volume record);

    int updateByPrimaryKey(Volume record);
}