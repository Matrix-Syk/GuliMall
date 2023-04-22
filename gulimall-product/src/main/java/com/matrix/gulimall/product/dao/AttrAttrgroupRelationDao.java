package com.matrix.gulimall.product.dao;

import com.matrix.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ????&???Է???????
 * 
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-06 22:16:26
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBatchAttrIds(@Param("attrIds") List<Long> attrIds);
}
