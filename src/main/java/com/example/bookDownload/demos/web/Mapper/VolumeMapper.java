package com.example.bookDownload.demos.web.Mapper;

import com.example.bookDownload.demos.web.Entity.Volume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface VolumeMapper {
    List<Volume> queryAll();

    List<Volume> queryByNovel(
            int novelId
    );

    List<Volume> queryByVolume(
            int volumeId
    );

    List<Volume> queryByNovelVolume(
            @Param("volumeName") String volumeName,
            @Param("novelId") int novelId
    );

    int insertVolume (Volume volume);
}
