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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lzw
 * @date 2019/12/12 18:34
 */
@Data
@ApiModel(description="商品库存增加修改参数")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductSkuDto implements Serializable {

private static final long serialVersionUID = 900447705542164350L;

        List<SpecificationValueDto> specificationValueDtos;

/**
 * id
 */
@ApiModelProperty(value = "id",example = "0")
private Long id;

/**
 * 分类ID
 * Table:     f_product_sku
 * Column:    classify_id
 * Nullable:  true
 */
@ApiModelProperty(value = "分类ID",example = "0")
private Long classifyId;

/**
 * 商品ID
 * Table:     f_product_sku
 * Column:    pro_id
 * Nullable:  true
 */
@ApiModelProperty(value = "商品ID",example = "0")
private Long proId;

/**
 * 编码
 * Table:     f_product_sku
 * Column:    sku_sn
 * Nullable:  true
 */
@ApiModelProperty(value = "编码")
private String skuSn;

/**
 * 库存
 * Table:     f_product_sku
 * Column:    stock
 * Nullable:  true
 */
@ApiModelProperty(value = "库存",example = "0")
private Integer stock;

/**
 * 销售价
 * Table:     f_product_sku
 * Column:    sale_price
 * Nullable:  true
 */
@ApiModelProperty(value = "销售价",example = "0")
private BigDecimal salePrice;

/**
 * 市场价
 * Table:     f_product_sku
 * Column:    market_price
 * Nullable:  true
 */
@ApiModelProperty(value = "市场价",example = "0")
private BigDecimal marketPrice;

/**
 * 成本价
 * Table:     f_product_sku
 * Column:    cost_price
 * Nullable:  true
 */
@ApiModelProperty(value = "成本价",example = "0")
private BigDecimal costPrice;
        }
