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
import org.forbes.dal.entity.AttributeValue;
import org.forbes.dal.entity.ProductSku;
import org.forbes.dal.entity.SpecificationValue;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lzw
 * @date 2019/12/11 15:56
 */
@Data
@ApiModel(description="商品增加修改参数")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductDto implements Serializable{

    private static final long serialVersionUID = 7814229668578335245L;

    @ApiModelProperty("商品属性")
    List<AttributeValue> attributeValues;

    @ApiModelProperty("商品库存")
    List<ProductSkuDto> productSkuDtos;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message="主键ID为空",groups=UpdateValid.class)
    private Long id;


    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 分类id
     * Table:     f_product
     * Column:    classify_id
     * Nullable:  true
     */
    private Long classifyId;

    /**
     * 品牌ID
     * Table:     f_product
     * Column:    brand_id
     * Nullable:  true
     */
    private Long brandId;

    /**
     * 店铺ID
     * Table:     f_product
     * Column:    shop_id
     * Nullable:  true
     */
    private Long shopId;

    /**
     * 商品编码
     * Table:     f_product
     * Column:    pro_sn
     * Nullable:  true
     */
    private String proSn;

    /**
     * 商品名称
     * Table:     f_product
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * 品种
     * Table:     f_product
     * Column:    variety
     * Nullable:  true
     */
    private String variety;

    /**
     * 厚度单位为mm
     * Table:     f_product
     * Column:    thickness
     * Nullable:  true
     */
    private BigDecimal thickness;

    /**
     * 细度单位dtex
     * Table:     f_product
     * Column:    fineness
     * Nullable:  true
     */
    private BigDecimal fineness;

    /**
     * 颜色
     * Table:     f_product
     * Column:    color
     * Nullable:  true
     */
    private String color;

    /**
     * 功能
     * Table:     f_product
     * Column:    function
     * Nullable:  true
     */
    private String function;

    /**
     * Table:     f_product
     * Column:    unit_price
     * Nullable:  true
     */
    private BigDecimal unitPrice;

    /**
     * 单价
     * Table:     f_product
     * Column:    price
     * Nullable:  true
     */
    private BigDecimal price;

    /**
     * 单位
     * Table:     f_product
     * Column:    company
     * Nullable:  true
     */
    private String company;

    /**
     * 数量
     * Table:     f_product
     * Column:    number
     * Nullable:  true
     */
    private Integer number;

    /**
     * 集装箱重--改为件重
     * Table:     f_product
     * Column:    whole_weight
     * Nullable:  true
     */
    private String wholeWeight;

    /**
     * 产地
     * Table:     f_product
     * Column:    origin
     * Nullable:  true
     */
    private String origin;

    /**
     * 存放地
     * Table:     f_product
     * Column:    deposit
     * Nullable:  true
     */
    private String deposit;

    /**
     * 仓库
     * Table:     f_product
     * Column:    warehouse
     * Nullable:  true
     */
    private String warehouse;

    /**
     * 销售地址
     * Table:     f_product
     * Column:    sales_address
     * Nullable:  true
     */
    private String salesAddress;

    /**
     * 说明
     * Table:     f_product
     * Column:    explaindesc
     * Nullable:  true
     */
    private String explaindesc;

    /**
     * 实物验证
     * Table:     f_product
     * Column:    good_verify
     * Nullable:  true
     */
    private Integer goodVerify;

    /**
     * 质量验证
     * Table:     f_product
     * Column:    quality_verify
     * Nullable:  true
     */
    private Integer qualityVerify;

    /**
     * 质保书1无2有
     * Table:     f_product
     * Column:    quality_warranty
     * Nullable:  true
     */
    private Integer qualityWarranty;

    /**
     * 议价1有2无
     * Table:     f_product
     * Column:    bargaining
     * Nullable:  true
     */
    private Integer bargaining;

    /**
     * 延期保证金
     * Table:     f_product
     * Column:    extension_margin
     * Nullable:  true
     */
    private Integer extensionMargin;

    /**
     * 融资保证金
     * Table:     f_product
     * Column:    financing_margin
     * Nullable:  true
     */
    private Integer financingMargin;

    /**
     * 供方代运
     * Table:     f_product
     * Column:    supplier_delivery
     * Nullable:  true
     */
    private Integer supplierDelivery;

    /**
     * 先行赔付
     * Table:     f_product
     * Column:    compensation
     * Nullable:  true
     */
    private Integer compensation;

    /**
     * 视频验货
     * Table:     f_product
     * Column:    video_inspection
     * Nullable:  true
     */
    private Integer videoInspection;

    /**
     * 仓费快结
     * Table:     f_product
     * Column:    warehouse_charge
     * Nullable:  true
     */
    private Integer warehouseCharge;

    /**
     * 代运补贴
     * Table:     f_product
     * Column:    subsidy
     * Nullable:  true
     */
    private Integer subsidy;

    /**
     * 闪电开票
     * Table:     f_product
     * Column:    lightning_invoice
     * Nullable:  true
     */
    private Integer lightningInvoice;

    /**
     * 诚信店铺
     * Table:     f_product
     * Column:    honest_shop
     * Nullable:  true
     */
    private Integer honestShop;

    /**
     * 在途
     * Table:     f_product
     * Column:    on_the_way
     * Nullable:  true
     */
    private Integer onTheWay;

    /**
     * 定向
     * Table:     f_product
     * Column:    directional
     * Nullable:  true
     */
    private Integer directional;

    /**
     * 竞价
     * Table:     f_product
     * Column:    bidding_price
     * Nullable:  true
     */
    private Integer biddingPrice;

    /**
     * 现货挂牌
     * Table:     f_product
     * Column:    spot_listing
     * Nullable:  true
     */
    private Integer spotListing;

    /**
     * 供应链
     * Table:     f_product
     * Column:    supply_chain
     * Nullable:  true
     */
    private Integer supplyChain;

    /**
     * 担保交易
     * Table:     f_product
     * Column:    guarantee_pay
     * Nullable:  true
     */
    private Integer guaranteePay;

    /**
     * 纤维纤度
     * Table:     f_product
     * Column:    size
     * Nullable:  true
     */
    private BigDecimal size;

    /**
     * 长度
     * Table:     f_product
     * Column:    length
     * Nullable:  true
     */
    private BigDecimal length;

    /**
     * 断裂长度
     * Table:     f_product
     * Column:    break_length
     * Nullable:  true
     */
    private BigDecimal breakLength;

    /**
     * 延伸率
     * Table:     f_product
     * Column:    break_elongation
     * Nullable:  true
     */
    private BigDecimal breakElongation;

    /**
     * 回潮率
     * Table:     f_product
     * Column:    regain
     * Nullable:  true
     */
    private BigDecimal regain;

    /**
     * 卷曲度
     * Table:     f_product
     * Column:    crimp_degree
     * Nullable:  true
     */
    private Integer crimpDegree;

    /**
     * 倍长纤维
     * Table:     f_product
     * Column:    double_length
     * Nullable:  true
     */
    private BigDecimal doubleLength;

    /**
     * 白度
     * Table:     f_product
     * Column:    whiteness
     * Nullable:  true
     */
    private BigDecimal whiteness;

    /**
     * 疵点
     * Table:     f_product
     * Column:    defect
     * Nullable:  true
     */
    private BigDecimal defect;

    /**
     * 油污
     * Table:     f_product
     * Column:    oil
     * Nullable:  true
     */
    private BigDecimal oil;

    /**
     * 残硫物
     * Table:     f_product
     * Column:    sulfur
     * Nullable:  true
     */
    private BigDecimal sulfur;

    /**
     * 灼烧残留物
     * Table:     f_product
     * Column:    burn
     * Nullable:  true
     */
    private BigDecimal burn;

    /**
     * Table:     f_product
     * Column:    add_time
     * Nullable:  true
     */
    private String addTime;

    /**
     * 1上架2未上架3待审核4审核失败
     * Table:     f_product
     * Column:    state
     * Nullable:  true
     */
    private String state;

    /**
     * 库存
     * Table:     f_product
     * Column:    stock
     * Nullable:  true
     */
    private String stock;

    /**
     * 销售类别1现货直销2预售3竞价
     * Table:     f_product
     * Column:    sale_type
     * Nullable:  true
     */
    private Integer saleType;

}
