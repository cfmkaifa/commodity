package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: f_brand_attach
 */
@Data
public class BrandAttach{
    /**
     * 主键
     * Table:     f_brand_attach
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Table:     f_brand_attach
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 创建时间
     * Table:     f_brand_attach
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * 更新人
     * Table:     f_brand_attach
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 更新时间
     * Table:     f_brand_attach
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    /**
     * 数据ID
     * Table:     f_brand_attach
     * Column:    data_id
     * Nullable:  true
     */
    private Long dataId;

    /**
     * 排序
     * Table:     f_brand_attach
     * Column:    orders_sort
     * Nullable:  true
     */
    private Integer ordersSort;

    /**
     * 后缀
     * Table:     f_brand_attach
     * Column:    suffix
     * Nullable:  true
     */
    private String suffix;

    /**
     * 中文名称
     * Table:     f_brand_attach
     * Column:    cn_name
     * Nullable:  true
     */
    private String cnName;

    /**
     * Table:     f_brand_attach
     * Column:    en_name
     * Nullable:  true
     */
    private String enName;

    /**
     * 文件地址
     * Table:     f_brand_attach
     * Column:    file_path
     * Nullable:  true
     */
    private String filePath;

    /**
     * 0-大图，1-小图，3-中图
     * Table:     f_brand_attach
     * Column:    type
     * Nullable:  true
     */
    private String type;
}