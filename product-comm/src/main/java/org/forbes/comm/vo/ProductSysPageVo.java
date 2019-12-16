package org.forbes.comm.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/12/16 10:34
 * @Version 1.0
 **/
@Data
@ApiModel(description="总后台分页查询商品")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductSysPageVo implements Serializable{
    private static final long serialVersionUID = -8574062038633976395L;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String proSn;

    /**
     *单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    /**
     *商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     *库存
     */
    @ApiModelProperty(value = "库存")
    private String stock;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String state;


    /**
     * 商品附件
     */
    @ApiModelProperty(value = "商品附件")
    private ProductAttachVo productAttachVo;


    /**
     * 商品分类
     */
    @ApiModelProperty(value = "商品分类")
    private ProductClassifyVo productClassifyVo;

}
