package com.dji.sample.media.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.media.model.MediaFileEntity;

/**
 * 파일 매퍼 인터페이스
 * 미디어 파일 엔티티에 대한 데이터베이스 접근을 담당하는 매퍼입니다.
 * MyBatis-Plus의 BaseMapper를 상속받아 기본적인 CRUD 작업을 제공합니다.
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
public interface IFileMapper extends BaseMapper<MediaFileEntity> {
}
