package org.forbes.comm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.forbes.comm.constant.UpdateValid;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

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

    /**
     * 商品名称
     * Table:     f_product
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * 商品编码
     * Table:     f_product
     * Column:    pro_sn
     * Nullable:  true
     */
    private String proSn;

    /**
     * 品种
     * Table:     f_product
     * Column:    variety
     * Nullable:  true
     */
    private String variety;

    /**
     * 图片
     *
     */

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
     * 单价
     * Table:     f_product
     * Column:    price
     * Nullable:  true
     */
    private BigDecimal price;

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
     * 说明
     * Table:     f_product
     * Column:    explaindesc
     * Nullable:  true
     */
    private String explaindesc;

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

}
