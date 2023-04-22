package com.matrix.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.Query;
import com.matrix.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.matrix.gulimall.product.dao.AttrDao;
import com.matrix.gulimall.product.entity.AttrEntity;
import com.matrix.gulimall.product.service.AttrService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    private AttrAttrgroupRelationDao relationDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<AttrEntity> queryByIdList(List<Long> idList) {

        return baseMapper.selectBatchIds(idList);
    }

    @Override
    public PageUtils queryExclusive(@RequestParam Map<String, Object> params, List<Long> idList) {
        String key = (String) params.get("key");
        List<AttrEntity> attrEntities = baseMapper.queryExclusive(key, idList);
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params));
        page.setSize(attrEntities.size());
        page.setRecords(attrEntities);
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryCategoryAttr(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (categoryId != 0) {
            wrapper.eq("catelog_id", categoryId);
        }
        if (StringUtils.isNotEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public boolean saveAttr(AttrEntity attrEntity) {
        AttrEntity one = baseMapper.selectOne(new QueryWrapper<AttrEntity>().eq("attr_name", attrEntity.getAttrName()));
        if (one == null) {
            return baseMapper.insertAttrBackId(attrEntity) > 0;
        }
        return false;
    }

    @Transactional
    @Override
    public void removeAttrByIds(List<Long> asList) {
        baseMapper.deleteBatchIds(asList);
        relationDao.deleteBatchAttrIds(asList);
    }
}