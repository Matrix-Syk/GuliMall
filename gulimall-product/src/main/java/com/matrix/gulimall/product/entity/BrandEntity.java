package com.matrix.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.matrix.common.validator.group.AddGroup;
import com.matrix.common.validator.group.ListValue;
import com.matrix.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Ʒ?
 * 
 * @author matrix
 * @email sunlightcs@gmail.com
 * @date 2023-04-07 00:18:37
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@Null(message = "添加时只能为空",groups = {AddGroup.class})
	@NotNull(message = "修改时不能为空",groups = {UpdateGroup.class})
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo
	 */
	@NotBlank(message = "logo不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String logo;
	/**
	 * 描述
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示 1-显示]
	 */
	@ListValue(vals = {0,1},groups = {AddGroup.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须是个字母",groups = {AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull
	@Min(value = 0,groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
