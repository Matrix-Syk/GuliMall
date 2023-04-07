package com.matrix.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matrix.common.utils.PageUtils;
import com.matrix.gulimall.order.entity.OrderEntity;

import java.util.Map;

/**
 * ????
 *
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-08 00:08:36
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

