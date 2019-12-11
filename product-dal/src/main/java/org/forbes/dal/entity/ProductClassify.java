package org.forbes.dal.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Table: f_product_classify
 */
@Data
@ApiModel(description="商品分类信息")
@EqualsAndHashCode(callSuper = false)
@TableName("f_product_classify")
public class ProductClassify {
    /**
     * 主键
     * Table:     f_product_classify
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Table:     f_product_classify
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 创建时间
     * Table:     f_product_classify
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * 更新人
     * Table:     f_product_classify
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 更新时间
     * Table:     f_product_classify
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    /**
     * 分类编码
     * Table:     f_product_classify
     * Column:    classify_sn
     * Nullable:  true
     */
    private String classifySn;

    /**
     * 分类名称
     * Table:     f_product_classify
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * Table:     f_product_classify
     * Column:    parent_id
     * Nullable:  true
     */
    private Long parentId;

    /**
     * 等级
     * Table:     f_product_classify
     * Column:    level
     * Nullable:  true
     */
    private Long level;

    /**
     * 状态:0未启用1-启用
     * Table:     f_product_classify
     * Column:    state
     * Nullable:  true
     */
    private Long state;
}