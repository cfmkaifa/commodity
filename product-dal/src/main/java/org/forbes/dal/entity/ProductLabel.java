package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author lzw
 * @date 2019/12/13 13:39
 */
@Data
@ApiModel(description="标签信息")
@EqualsAndHashCode(callSuper = false)
@TableName("f_product_label")
public class ProductLabel extends BaseEntity {

    private static final long serialVersionUID = 354688354023745792L;

    /**
     * 商品ID
     * Table:     f_product_label
     * Column:    pro_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品ID",example = "0")
    private Long proId;

    /**
     * 标签名称
     * Table:     f_product_label
     * Column:    label_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称")
    private String labelName;

    /**
     * 排序
     * Table:     f_product_specification
     * Column:    order_sorts
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example = "0")
    private Integer orderSorts;

    /**
     * 备注
     * Table:     f_product_label
     * Column:    remarks
     * Nullable:  true
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
}