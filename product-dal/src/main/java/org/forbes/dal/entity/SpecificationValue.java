package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Table: f_specification_value
 */
@Data
@ApiModel(description="分类规格")
@EqualsAndHashCode(callSuper = false)
@TableName("f_specification_value")
public class SpecificationValue extends BaseEntity{
    
	private static final long serialVersionUID = 8259619795373167378L;

	/**
     * 分类ID
     * Table:     f_specification_value
     * Column:    classify_id
     * Nullable:  true
     */
	@ApiModelProperty("分类ID")
    private Long classifyId;

    /**
     * 商品ID
     * Table:     f_specification_value
     * Column:    pro_id
     * Nullable:  true
     */
    @ApiModelProperty("商品ID")
    private Long proId;

    /**
     * 规格ID
     * Table:     f_specification_value
     * Column:    spec_id
     * Nullable:  true
     */
    @ApiModelProperty("规格ID")
    private Long specId;


    /***规格值
     */
    @ApiModelProperty("规格值")
    private String specVal;
    
    /***skuId
     */
    @ApiModelProperty("库存id")
    private Long skuId;
}