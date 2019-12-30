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
import org.forbes.comm.model.AttributeValueDto;
import org.forbes.comm.model.ProductAttachDto;
import org.forbes.comm.model.ProductSkuDto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lzw
 * @date 2019/12/16 9:49
 */
@Data
@ApiModel(description="商品查询返回对象")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductAttvalueVo implements Serializable{

    private static final long serialVersionUID = 8848645335864724946L;

    /**
     * 数据ID
     * Table:     f_product_attach
     * Column:    data_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据ID",example = "0")
    private Long dataId;

    /**
     * 后缀
     * Table:     f_product_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty("后缀")
    private String suffix;

    /**
     * 中文名称
     * Table:     f_product_attach
     * Column:    cn_name
     * Nullable:  true
     */
    @ApiModelProperty("中文名称")
    private String cnName;

    /**
     * Table:     f_product_attach
     * Column:    en_name
     * Nullable:  true
     */
    @ApiModelProperty("英文名")
    private String enName;

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
     * Table:     f_attribute_value
     * Column:    orders_sort
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example = "0")
    private Integer ordersSort;

    /**
     * 属性值
     * Table:     f_attribute_value
     * Column:    attribute_value
     * Nullable:  true
     */
    @ApiModelProperty("属性值")
    private String attributeValue;


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
    @ApiModelProperty("编码")
    private String skuSn;

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

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "id",example = "0")
    private Long id;

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
    @ApiModelProperty(value = "品牌id",example = "0")
    private Long brandId;

    /**
     * 店铺ID
     * Table:     f_product
     * Column:    shop_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "店铺id",example = "0")
    private Long shopId;

    /**
     * 商品编码
     * Table:     f_product
     * Column:    pro_sn
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品编码")
    private String proSn;

    /**
     * 商品名称
     * Table:     f_product
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 品种
     * Table:     f_product
     * Column:    variety
     * Nullable:  true
     */
    @ApiModelProperty(value = "品种")
    private String variety;

    /**
     * 厚度单位为mm
     * Table:     f_product
     * Column:    thickness
     * Nullable:  true
     */
    @ApiModelProperty(value = "厚度单位为mm",example = "0")
    private BigDecimal thickness;

    /**
     * 细度单位dtex
     * Table:     f_product
     * Column:    fineness
     * Nullable:  true
     */
    @ApiModelProperty(value = "细度单位dtex",example = "0")
    private BigDecimal fineness;

    /**
     * 颜色
     * Table:     f_product
     * Column:    color
     * Nullable:  true
     */
    @ApiModelProperty("颜色")
    private String color;

    /**
     * 功能
     * Table:     f_product
     * Column:    function
     * Nullable:  true
     */
    @ApiModelProperty("功能")
    private String function;

    /**
     * Table:     f_product
     * Column:    unit_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "细度单位dtex",example = "0")
    private BigDecimal unitPrice;

    /**
     * 单价
     * Table:     f_product
     * Column:    price
     * Nullable:  true
     */
    @ApiModelProperty(value = "单价",example = "0")
    private BigDecimal price;

    /**
     * 单位
     * Table:     f_product
     * Column:    company
     * Nullable:  true
     */
    @ApiModelProperty("单位")
    private String company;

    /**
     * 数量
     * Table:     f_product
     * Column:    number
     * Nullable:  true
     */
    @ApiModelProperty(value = "数量",example = "0")
    private Integer number;

    /**
     * 集装箱重--改为件重
     * Table:     f_product
     * Column:    whole_weight
     * Nullable:  true
     */
    @ApiModelProperty("集装箱重")
    private String wholeWeight;

    /**
     * 产地
     * Table:     f_product
     * Column:    origin
     * Nullable:  true
     */
    @ApiModelProperty("产地")
    private String origin;

    /**
     * 存放地
     * Table:     f_product
     * Column:    deposit
     * Nullable:  true
     */
    @ApiModelProperty("存放地")
    private String deposit;

    /**
     * 仓库
     * Table:     f_product
     * Column:    warehouse
     * Nullable:  true
     */
    @ApiModelProperty("仓库")
    private String warehouse;

    /**
     * 销售地址
     * Table:     f_product
     * Column:    sales_address
     * Nullable:  true
     */
    @ApiModelProperty("销售地址")
    private String salesAddress;

    /**
     * 说明
     * Table:     f_product
     * Column:    explaindesc
     * Nullable:  true
     */
    @ApiModelProperty("说明")
    private String explaindesc;

    /**
     * 实物验证
     * Table:     f_product
     * Column:    good_verify
     * Nullable:  true
     */
    @ApiModelProperty(value = "实物验证",example = "0")
    private Integer goodVerify;

    /**
     * 质量验证
     * Table:     f_product
     * Column:    quality_verify
     * Nullable:  true
     */
    @ApiModelProperty(value = "质量验证",example = "0")
    private Integer qualityVerify;

    /**
     * 质保书1无2有
     * Table:     f_product
     * Column:    quality_warranty
     * Nullable:  true
     */
    @ApiModelProperty(value = "质保书1无2有",example = "0")
    private Integer qualityWarranty;

    /**
     * 议价1有2无
     * Table:     f_product
     * Column:    bargaining
     * Nullable:  true
     */
    @ApiModelProperty(value = "议价1有2无",example = "0")
    private Integer bargaining;

    /**
     * 延期保证金
     * Table:     f_product
     * Column:    extension_margin
     * Nullable:  true
     */
    @ApiModelProperty(value = "延期保证金",example = "0")
    private Integer extensionMargin;

    /**
     * 融资保证金
     * Table:     f_product
     * Column:    financing_margin
     * Nullable:  true
     */
    @ApiModelProperty(value = "融资保证金",example = "0")
    private Integer financingMargin;

    /**
     * 供方代运
     * Table:     f_product
     * Column:    supplier_delivery
     * Nullable:  true
     */
    @ApiModelProperty(value = "供方代运",example = "0")
    private Integer supplierDelivery;

    /**
     * 先行赔付
     * Table:     f_product
     * Column:    compensation
     * Nullable:  true
     */
    @ApiModelProperty(value = "先行赔付",example = "0")
    private Integer compensation;

    /**
     * 视频验货
     * Table:     f_product
     * Column:    video_inspection
     * Nullable:  true
     */
    @ApiModelProperty(value = "视频验货",example = "0")
    private Integer videoInspection;

    /**
     * 仓费快结
     * Table:     f_product
     * Column:    warehouse_charge
     * Nullable:  true
     */
    @ApiModelProperty(value = "仓费快结",example = "0")
    private Integer warehouseCharge;

    /**
     * 代运补贴
     * Table:     f_product
     * Column:    subsidy
     * Nullable:  true
     */
    @ApiModelProperty(value = "代运补贴",example = "0")
    private Integer subsidy;

    /**
     * 闪电开票
     * Table:     f_product
     * Column:    lightning_invoice
     * Nullable:  true
     */
    @ApiModelProperty(value = "闪电开票",example = "0")
    private Integer lightningInvoice;

    /**
     * 诚信店铺
     * Table:     f_product
     * Column:    honest_shop
     * Nullable:  true
     */
    @ApiModelProperty(value = "诚信店铺",example = "0")
    private Integer honestShop;

    /**
     * 在途
     * Table:     f_product
     * Column:    on_the_way
     * Nullable:  true
     */
    @ApiModelProperty(value = "在途",example = "0")
    private Integer onTheWay;

    /**
     * 定向
     * Table:     f_product
     * Column:    directional
     * Nullable:  true
     */
    @ApiModelProperty(value = "定向",example = "0")
    private Integer directional;

    /**
     * 竞价
     * Table:     f_product
     * Column:    bidding_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "竞价",example = "0")
    private Integer biddingPrice;

    /**
     * 现货挂牌
     * Table:     f_product
     * Column:    spot_listing
     * Nullable:  true
     */
    @ApiModelProperty(value = "现货挂牌",example = "0")
    private Integer spotListing;

    /**
     * 供应链
     * Table:     f_product
     * Column:    supply_chain
     * Nullable:  true
     */
    @ApiModelProperty(value = "供应链",example = "0")
    private Integer supplyChain;

    /**
     * 担保交易
     * Table:     f_product
     * Column:    guarantee_pay
     * Nullable:  true
     */
    @ApiModelProperty(value = "担保交易",example = "0")
    private Integer guaranteePay;

    /**
     * 纤维纤度
     * Table:     f_product
     * Column:    size
     * Nullable:  true
     */
    @ApiModelProperty(value = "纤维纤度",example = "0")
    private BigDecimal size;

    /**
     * 长度
     * Table:     f_product
     * Column:    length
     * Nullable:  true
     */
    @ApiModelProperty(value = "长度",example = "0")
    private BigDecimal length;

    /**
     * 断裂长度
     * Table:     f_product
     * Column:    break_length
     * Nullable:  true
     */
    @ApiModelProperty(value = "断裂长度",example = "0")
    private BigDecimal breakLength;

    /**
     * 延伸率
     * Table:     f_product
     * Column:    break_elongation
     * Nullable:  true
     */
    @ApiModelProperty(value = "延伸率",example = "0")
    private BigDecimal breakElongation;

    /**
     * 回潮率
     * Table:     f_product
     * Column:    regain
     * Nullable:  true
     */
    @ApiModelProperty(value = "回潮率",example = "0")
    private BigDecimal regain;

    /**
     * 卷曲度
     * Table:     f_product
     * Column:    crimp_degree
     * Nullable:  true
     */
    @ApiModelProperty(value = "卷曲度",example = "0")
    private Integer crimpDegree;

    /**
     * 倍长纤维
     * Table:     f_product
     * Column:    double_length
     * Nullable:  true
     */
    @ApiModelProperty(value = "倍长纤维",example = "0")
    private BigDecimal doubleLength;

    /**
     * 白度
     * Table:     f_product
     * Column:    whiteness
     * Nullable:  true
     */
    @ApiModelProperty(value = "白度",example = "0")
    private BigDecimal whiteness;

    /**
     * 疵点
     * Table:     f_product
     * Column:    defect
     * Nullable:  true
     */
    @ApiModelProperty(value = "疵点",example = "0")
    private BigDecimal defect;

    /**
     * 油污
     * Table:     f_product
     * Column:    oil
     * Nullable:  true
     */
    @ApiModelProperty(value = "油污",example = "0")
    private BigDecimal oil;

    /**
     * 残硫物
     * Table:     f_product
     * Column:    sulfur
     * Nullable:  true
     */
    @ApiModelProperty(value = "残硫物",example = "0")
    private BigDecimal sulfur;

    /**
     * 灼烧残留物
     * Table:     f_product
     * Column:    burn
     * Nullable:  true
     */
    @ApiModelProperty(value = "灼烧残留物",example = "0")
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
    @ApiModelProperty("1上架2未上架3待审核4审核失败")
    private String state;

    /**
     * 库存
     * Table:     f_product
     * Column:    stock
     * Nullable:  true
     */
    @ApiModelProperty("库存")
    private String stock;

    /**
     * 销售类别1现货直销2预售3竞价
     * Table:     f_product
     * Column:    sale_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "销售类别1现货直销2预售3竞价",example = "0")
    private Integer saleType;
}
