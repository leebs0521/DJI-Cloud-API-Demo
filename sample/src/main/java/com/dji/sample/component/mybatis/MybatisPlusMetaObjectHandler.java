package com.dji.sample.component.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * MyBatis Plus 메타 객체 핸들러 클래스
 * 
 * 데이터베이스 작업 시 자동으로 필드 값을 설정하는 핸들러입니다.
 * 생성 시간과 수정 시간을 자동으로 설정하는 기능을 제공합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * 데이터베이스에 삽입할 때 자동으로 필드를 채웁니다.
     * 
     * 엔티티가 데이터베이스에 삽입될 때 createTime과 updateTime 필드를
     * 현재 시간으로 자동 설정합니다.
     * 
     * @param metaObject 메타 객체
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 생성 시간을 현재 시간으로 설정
        this.strictInsertFill(metaObject, "createTime", Long.class,
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        // 수정 시간을 현재 시간으로 설정
        this.strictInsertFill(metaObject, "updateTime", Long.class,
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * 데이터를 업데이트할 때 자동으로 필드를 채웁니다.
     * 
     * 엔티티가 데이터베이스에서 업데이트될 때 updateTime 필드를
     * 현재 시간으로 자동 설정합니다.
     * 
     * @param metaObject 메타 객체
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 수정 시간을 현재 시간으로 설정
        this.strictUpdateFill(metaObject, "updateTime", Long.class,
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
