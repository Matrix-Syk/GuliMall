package com.matrix.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.common.utils.PageUtils;
import com.matrix.gulimall.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * ??ɱ???Ʒ????
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-07 23:49:24
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
