package com.matrix.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.Query;
import com.matrix.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.matrix.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.matrix.gulimall.product.service.AttrAttrgroupRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public boolean removeRelation(List<Map<String, Long>> relations) {

        for (Map<String, Long> relation : relations) {
            Long attrGroupId = relation.get("attrGroupId");
            Long attrId = relation.get("attrId");
            baseMapper.delete(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId).eq("attr_id", attrId));
        }
        return true;
    }

    @Override
    public List<AttrAttrgroupRelationEntity> queryByGroupId(Long attrGroupId) {

        return baseMapper.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId));
    }

    @Override
    public AttrAttrgroupRelationEntity queryByAttrId(Long attrId) {
        return baseMapper.selectByAttrId(attrId);
    }


}