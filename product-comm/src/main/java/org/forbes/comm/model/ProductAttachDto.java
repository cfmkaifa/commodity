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

/**
 * @author lzw
 * @date 2019/12/14 16:44
 */
@Data
@ApiModel(description="附件")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductAttachDto implements Serializable{

    private static final long serialVersionUID = 3170315077900811318L;

    /**
     * id
     */
    @ApiModelProperty(value = "数据ID(商品id)",example = "0")
    private Long id;

    /**
     * 数据ID
     * Table:     f_product_attach
     * Column:    data_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据ID",example = "0")
    private Long dataId;

    /**
     * 排序
     * Table:     f_product_attach
     * Column:    orders_sort
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example = "0")
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
