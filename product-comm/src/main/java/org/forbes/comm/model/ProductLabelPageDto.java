package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/1/8 10:58
 */
@Data
@ApiModel(description="商品标签分页查询参数")
@EqualsAndHashCode(callSuper = false)
public class ProductLabelPageDto implements Serializable {

    private static final long serialVersionUID = 4060765538268242675L;

    /**
     * 标签名称
     * Table:     f_product_label
     * Column:    label_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称",required = true)
    private String LabelName;
}
