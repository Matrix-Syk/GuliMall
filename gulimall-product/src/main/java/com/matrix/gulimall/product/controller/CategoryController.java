package com.matrix.gulimall.product.controller;

import com.matrix.common.utils.R;
import com.matrix.gulimall.product.entity.CategoryEntity;
import com.matrix.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * ??Ʒ???????
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-07 00:18:37
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 商品分类树形结构
     */
    @RequestMapping("/list/tree")
    public R list() {
        List<CategoryEntity> tree = categoryService.listWithTree();
        return R.ok().put("data", tree);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
//  @RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//  @RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category) {
        categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category) {
        categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     *
     * @RequestBody: 获取请求体，必须发送post请求，springmvc自动将请求体json转换为对应对象
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds) {
        Boolean aBoolean = categoryService.removeMenuByIds(Arrays.asList(catIds));
        return R.ok().put("code", aBoolean ? 0 : 1).put("msg", aBoolean ?"删除成功":"删除失败");
    }

}
