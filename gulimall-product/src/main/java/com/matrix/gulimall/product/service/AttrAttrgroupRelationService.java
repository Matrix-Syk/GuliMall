package com.matrix.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.common.utils.PageUtils;
import com.matrix.gulimall.product.entity.AttrAttrgroupRelationEntity;

import java.util.List;
import java.util.Map;

public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean removeRelation(List<Map<String, Long>> relations);

    List<AttrAttrgroupRelationEntity> queryByGroupId(Long attrGroupId);
}

