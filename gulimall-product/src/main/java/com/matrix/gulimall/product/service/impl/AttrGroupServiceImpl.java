package com.matrix.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.Query;
import com.matrix.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.matrix.gulimall.product.dao.AttrGroupDao;
import com.matrix.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.matrix.gulimall.product.entity.AttrGroupEntity;
import com.matrix.gulimall.product.service.AttrGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private AttrAttrgroupRelationDao relationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        IPage<AttrGroupEntity> iPage = new Query<AttrGroupEntity>().getPage(params);
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }
        if (categoryId != 0) {
            wrapper.eq("catelog_id", categoryId);
        }
        IPage<AttrGroupEntity> page = this.page(iPage, wrapper);
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void changeGroup(AttrGroupEntity attrGroup) {
        baseMapper.updateById(attrGroup);
        List<AttrAttrgroupRelationEntity> relations = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroup.getAttrGroupId()));
        if (relations.size() > 0) {
            List<Long> groupIds = relations.stream().map(AttrAttrgroupRelationEntity::getAttrGroupId).collect(Collectors.toList());
            relationDao.deleteBatchGroupIds(groupIds);
        }
    }

}