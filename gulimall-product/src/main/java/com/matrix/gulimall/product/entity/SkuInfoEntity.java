package com.matrix.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * sku??Ϣ
 * 
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-06 22:16:26
 */
@Data
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * skuId
	 */
	@TableId
	private Long skuId;
	/**
	 * spuId
	 */
	private Long spuId;
	/**
	 * sku???
	 */
	private String skuName;
	/**
	 * sku????????
	 */
	private String skuDesc;
	/**
	 * ????????id
	 */
	private Long catalogId;
	/**
	 * Ʒ??id
	 */
	private Long brandId;
	/**
	 * Ĭ??ͼƬ
	 */
	private String skuDefaultImg;
	/**
	 * ???
	 */
	private String skuTitle;
	/**
	 * ?????
	 */
	private String skuSubtitle;
	/**
	 * ?۸
	 */
	private BigDecimal price;
	/**
	 * ????
	 */
	private Long saleCount;

}
