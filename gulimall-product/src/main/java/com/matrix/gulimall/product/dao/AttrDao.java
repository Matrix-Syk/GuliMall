package com.matrix.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matrix.gulimall.product.entity.AttrEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ??Ʒ?
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-06 22:16:26
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

    List<AttrEntity> queryExclusive(@Param("key") String key, @Param("idList") List<Long> idList);

    Long insertAttrBackId(AttrEntity attrEntity);
}
