package com.matrix.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.Query;
import com.matrix.gulimall.product.dao.CategoryBrandRelationDao;
import com.matrix.gulimall.product.entity.CategoryBrandRelationEntity;
import com.matrix.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Boolean association(CategoryBrandRelationEntity relation) {
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<CategoryBrandRelationEntity>()
                .eq("brand_id", relation.getBrandId())
                .eq("catelog_id", relation.getCatelogId());
        CategoryBrandRelationEntity one = baseMapper.selectOne(wrapper);
        if (one == null) {
            return baseMapper.insert(relation) > 0;
        }
        return false;
    }

    @Override
    public List<CategoryBrandRelationEntity> queryBrandCategroy(Long brandId) {
        List<CategoryBrandRelationEntity> relationEntityList = baseMapper.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
        return relationEntityList;
    }

}