package org.forbes.dal.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Table: f_product_attach
 */
@Data
@ApiModel(description="库存信息")
@EqualsAndHashCode(callSuper = false)
@TableName("f_product_attach")
public class ProductAttach extends BaseEntity{

    private static final long serialVersionUID = 1968445381323876705L;
    /**
     * 数据ID
     * Table:     f_product_attach
     * Column:    data_id
     * Nullable:  true
     */
    @ApiModelProperty("数据ID")
    private Long dataId;

    /**
     * 排序
     * Table:     f_product_attach
     * Column:    orders_sort
     * Nullable:  true
     */
    @ApiModelProperty("排序")
    private Integer ordersSort;

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
}