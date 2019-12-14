package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: f_product_attach
 */
@Data
public class ProductAttach extends BaseEntity{

    private static final long serialVersionUID = 1968445381323876705L;
    /**
     * 数据ID
     * Table:     f_product_attach
     * Column:    data_id
     * Nullable:  true
     */
    private Long dataId;

    /**
     * 排序
     * Table:     f_product_attach
     * Column:    orders_sort
     * Nullable:  true
     */
    private Integer ordersSort;

    /**
     * 后缀
     * Table:     f_product_attach
     * Column:    suffix
     * Nullable:  true
     */
    private String suffix;

    /**
     * 中文名称
     * Table:     f_product_attach
     * Column:    cn_name
     * Nullable:  true
     */
    private String cnName;

    /**
     * Table:     f_product_attach
     * Column:    en_name
     * Nullable:  true
     */
    private String enName;

    /**
     * 文件地址
     * Table:     f_product_attach
     * Column:    file_path
     * Nullable:  true
     */
    private String filePath;

    /**
     * 0-大图，1-小图，3-中图
     * Table:     f_product_attach
     * Column:    type
     * Nullable:  true
     */
    private String type;
}