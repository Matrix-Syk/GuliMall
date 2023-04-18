package com.matrix.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.Query;
import com.matrix.gulimall.product.dao.AttrDao;
import com.matrix.gulimall.product.entity.AttrEntity;
import com.matrix.gulimall.product.service.AttrService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

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
    public IPage<AttrEntity> queryPageByIds(Map<String, Object> params, List<Long> idList) {

        return null;
    }

}