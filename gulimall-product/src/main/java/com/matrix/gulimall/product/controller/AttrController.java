package com.matrix.gulimall.product.controller;

import com.matrix.common.utils.PageUtils;
import com.matrix.common.utils.R;
import com.matrix.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.matrix.gulimall.product.entity.AttrEntity;
import com.matrix.gulimall.product.service.AttrAttrgroupRelationService;
import com.matrix.gulimall.product.service.AttrService;
import com.matrix.gulimall.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * ??Ʒ?
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-06 22:16:26
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService relationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        attrService.saveAttr(attrEntity);
        if (attrEntity.getAttrId() == null) {
            return R.error().put("msg","同名属性已存在");
        }
        Long groupId = attr.getAttrGroupId();
        if (groupId != null) {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(groupId);
            relationEntity.setAttrId(attrEntity.getAttrId());
            relationService.save(relationEntity);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrEntity attr) {
        attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeAttrByIds(Arrays.asList(attrIds));
        return R.ok();
    }

    //product/attr/base/list/0
    @GetMapping("base/list/{categoryId}")
    public R baseAttrList(@RequestParam Map<String, Object> params, @PathVariable Long categoryId) {
        PageUtils page = attrService.queryCategoryAttr(params, categoryId);
        return R.ok().put("page", page);
    }
}
