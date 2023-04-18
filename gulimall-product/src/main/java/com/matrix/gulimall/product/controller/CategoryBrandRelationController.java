package com.matrix.gulimall.product.controller;

import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.R;
import com.matrix.gulimall.product.entity.BrandEntity;
import com.matrix.gulimall.product.entity.CategoryBrandRelationEntity;
import com.matrix.gulimall.product.entity.CategoryEntity;
import com.matrix.gulimall.product.service.BrandService;
import com.matrix.gulimall.product.service.CategoryBrandRelationService;
import com.matrix.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Ʒ?Ʒ???????
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-07 00:18:37
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService relationService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = relationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//  @RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id) {
        CategoryBrandRelationEntity categoryBrandRelation = relationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//  @RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        // categoryBrandRelationService.save(categoryBrandRelation);
        CategoryEntity categoryEntity = categoryService.getById(categoryBrandRelation.getCatelogId());
        BrandEntity brandEntity = brandService.getById(categoryBrandRelation.getBrandId());
        if (categoryEntity != null) {
            categoryBrandRelation.setCatelogName(categoryEntity.getName());
        }
        if (brandEntity != null) {
            categoryBrandRelation.setBrandName(brandEntity.getName());
        }
        Boolean b = relationService.association(categoryBrandRelation);
        if (b) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        relationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids) {
        relationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/catelog/list")
    public R getCategoryBrandRelation(@RequestParam Long brandId) {
        List<CategoryBrandRelationEntity> relations = relationService.queryBrandCategroy(brandId);
        return R.ok().put("data",relations);
    }

}
