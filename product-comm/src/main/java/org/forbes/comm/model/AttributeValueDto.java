package org.forbes.comm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.forbes.comm.constant.UpdateValid;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lzw
 * @date 2019/12/13 13:39
 */
@Data
@ApiModel(description="属性参数")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AttributeValueDto implements Serializable{

    private static final long serialVersionUID = 5416410638744284073L;

    /**
     * id
     */
    @ApiModelProperty(value = "id",example = "0")
    private Long id;

    /**
     * Table:     f_attribute_value
     * Column:    orders_sort
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example = "0")
    private Integer ordersSort;

    /**
     * 分类ID
     * Table:     f_attribute_value
     * Column:    classify_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "分类ID",example = "0")
    private Long classifyId;

    /**
     * 商品ID
     * Table:     f_attribute_value
     * Column:    pro_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品ID",example = "0")
    private Long proId;

    /**
     * 属性值
     * Table:     f_attribute_value
     * Column:    attribute_value
     * Nullable:  true
     */
    @ApiModelProperty(value = "属性值",example = "0")
    private String attributeValue;
}
