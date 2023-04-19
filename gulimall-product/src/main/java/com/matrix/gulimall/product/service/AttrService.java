package com.matrix.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.common.utils.PageUtils;
import com.matrix.gulimall.product.entity.AttrEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * ??Ʒ?
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-06 22:16:26
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<AttrEntity> queryByIdList(List<Long> idList);

    PageUtils queryExclusive(@RequestParam Map<String, Object> params, List<Long> idList);
}

