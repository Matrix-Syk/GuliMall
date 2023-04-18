package com.matrix.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.common.utils.PageUtils;
import com.matrix.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * ??Ʒ???????
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-07 00:18:37
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    Boolean removeMenuByIds(List<Long> asList);

    Long[] queryPath(Long catelogId);
}

