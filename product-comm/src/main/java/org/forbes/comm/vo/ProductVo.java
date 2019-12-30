package org.forbes.comm.vo;

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
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lzw
 * @date 2019/12/11 15:51
 */
@Data
@ApiModel(description="商品查询返回对象")
@EqualsAndHashCode(callSuper = false)
public class ProductVo implements Serializable {

    private static final long serialVersionUID = 2549085622563695034L;

    /**
     * id
     */
    @ApiModelProperty(value = "商品id",example = "0")
    private Long id;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 分类id
     * Table:     f_product
     * Column:    classify_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "分类id",example = "0")
    private Long classifyId;

    /**
     * 品牌ID
     * Table:     f_product
     * Column:    brand_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "品牌ID",example = "0")
    private Long brandId;

    /**
     * 店铺ID
     * Table:     f_product
     * Column:    shop_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "店铺ID",example = "0")
    private Long shopId;

    /**
     * 商品编码
     * Table:     f_product
     * Column:    pro_sn
     * Nullable:  true
     */
    @ApiModelProperty("商品编码")
    private String proSn;

    /**
     * 商品名称
     * Table:     f_product
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty("商品名称")
    private String name;

    /**
     * 品种
     * Table:     f_product
     * Column:    variety
     * Nullable:  true
     */
    @ApiModelProperty("品种")
    private String variety;

    /**
     * 1上架2未上架3待审核4审核失败
     * Table:     f_product
     * Column:    state
     * Nullable:  true
     */
    @ApiModelProperty("1上架2未上架3待审核4审核失败")
    private String state;

    /**
     * 文件地址
     * Table:     f_product_attach
     * Column:    file_path
     * Nullable:  true
     */
    @ApiModelProperty("文件地址")
    private String filePath;

    /**
     * 0-大图，1-小图，3-中图
     * Table:     f_product_attach
     * Column:    type
     * Nullable:  true
     */
    @ApiModelProperty("0-大图，1-小图，3-中图")
    private String type;

    /**
     * 单价
     * Table:     f_product
     * Column:    price
     * Nullable:  true
     */
    @ApiModelProperty(value = "单价",required = true,example = "0")
    private BigDecimal price;

    /**
     * 库存
     * Table:     f_product
     * Column:    stock
     * Nullable:  true
     */
    @ApiModelProperty("库存")
    private String stock;
}
