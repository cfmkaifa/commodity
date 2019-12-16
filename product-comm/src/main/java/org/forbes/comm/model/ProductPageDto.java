package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @author lzw
 * @date 2019/12/11 15:56
 */
@Data
@ApiModel(description="商品查询参数")
@EqualsAndHashCode(callSuper = false)
public class ProductPageDto implements Serializable{

    private static final long serialVersionUID = 6314536822829481483L;
    /**
     * 1上架2未上架3待审核4审核失败
     * Table:     f_product
     * Column:    state
     * Nullable:  true
     */
    @ApiModelProperty(value = "1上架2未上架3待审核4审核失败")
    private String state;

    /**
     * 商品编码
     * Table:     f_product
     * Column:    pro_sn
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品编码")
    private String proSn;
}
