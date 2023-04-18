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

import java.util.*;
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

    @Override
    public Boolean removeMenuByIds(List<Long> asList) {
        for (Long aLong : asList) {
            CategoryEntity categoryEntity = baseMapper.selectById(aLong);
            if (categoryEntity == null) {
                return false;
            } else if (categoryEntity.getChildren() == null) {
                // 逻辑删除
                baseMapper.deleteById(categoryEntity.getCatId());
                return true;
            }
        }
        return false;
    }

    @Override
    public Long[] queryPath(Long id) {
        List<Long> path = new ArrayList<>();
        if (id != null) {
            path.add(id);
            getPathList(id, path);
        }
        Collections.reverse(path);
        Long[] pathes = (Long[]) path.toArray(new Long[path.size()]);
        return pathes;
    }

    public void getPathList(Long id, List<Long> pathes) {
        Long parentCid = baseMapper.selectById(id).getParentCid();
        if (parentCid != 0){
            pathes.add(parentCid);
            getPathList(parentCid,pathes);
        }
    }
}