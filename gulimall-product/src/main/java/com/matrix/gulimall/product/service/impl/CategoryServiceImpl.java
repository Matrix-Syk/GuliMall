package com.matrix.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.Query;
import com.matrix.gulimall.product.dao.CategoryDao;
import com.matrix.gulimall.product.entity.CategoryEntity;
import com.matrix.gulimall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);
        List<CategoryEntity> levl1 = categoryEntityList.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .peek((cat) -> cat.setChildren(getChildrens(cat, categoryEntityList)))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());

        return levl1;
    }

    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> childrens = all.stream()
                .filter((categoryEntity -> categoryEntity.getParentCid() == root.getCatId()))
                .map((cat -> {
                    cat.setChildren(getChildrens(cat, all));
                    return cat;
                })).collect(Collectors.toList());
        return childrens;
    }

}