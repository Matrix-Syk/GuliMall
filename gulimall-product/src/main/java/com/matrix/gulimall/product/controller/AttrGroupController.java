package com.matrix.gulimall.product.controller;

import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.R;
import com.matrix.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.matrix.gulimall.product.entity.AttrEntity;
import com.matrix.gulimall.product.entity.AttrGroupEntity;
import com.matrix.gulimall.product.service.AttrAttrgroupRelationService;
import com.matrix.gulimall.product.service.AttrGroupService;
import com.matrix.gulimall.product.service.AttrService;
import com.matrix.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * ???Է??
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-07 00:18:37
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private AttrAttrgroupRelationService relationService;

    @Resource
    private AttrService attrService;

    /**
     * 列表
     */
    @RequestMapping("/list/{categoryId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("categoryId") Long categoryId) {
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params, categoryId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
//  @RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] categoryPath = categoryService.queryPath(catelogId);
        attrGroup.setCatelogPath(categoryPath);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//  @RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    /**
     * 属性分组关联属性
     */
    @GetMapping("/{attrGroupId}/attr/relation")
    public R getAttrBrandRelation(@PathVariable Long attrGroupId) {
        List<AttrAttrgroupRelationEntity> relationList = relationService.queryByGroupId(attrGroupId);
        // 分组下无已关联属性
        if (CollectionUtils.isEmpty(relationList)) {
            return R.error().put("msg", "该分组下无属性").put("code", 10002);
        }
        List<Long> idList = relationList.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        List<AttrEntity> attrEntities = attrService.queryByIdList(idList);
        return R.ok().put("data", attrEntities);
    }

    /**
     * 属性分组删除关联属性
     */
    @PostMapping("/attr/relation/delete")
    public R deleteAttrBrandRelation(@RequestBody List<Map<String, Long>> relations) {
        relationService.removeRelation(relations);
        return R.ok();
    }

    /**
     * 属性分组删除关联属性
     */
    @GetMapping("/{attrGroupId}/noattr/relation")
    public R attrBrandRelationPage(@RequestParam Map<String, Object> params, @PathVariable Long attrGroupId) {
        List<AttrAttrgroupRelationEntity> relations = relationService.queryByGroupId(attrGroupId);
        List<Long> idList = relations.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        PageUtils page = attrService.queryExclusive(params, idList);
        return R.ok().put("page", page);
    }

    @PostMapping("/attr/relation")
    public R groupAddAttr(@RequestBody List<AttrAttrgroupRelationEntity> relations) {
        relationService.saveBatch(relations);
        return R.ok();
    }
}
