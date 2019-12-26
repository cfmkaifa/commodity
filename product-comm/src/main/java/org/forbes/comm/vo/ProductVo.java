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
@Accessors(chain = true)
public class ProductVo implements Serializable {

    private static final long serialVersionUID = 2549085622563695034L;

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
     * 1上架2未上架3待审核4审核失败
     * Table:     f_product
     * Column:    state
     * Nullable:  true
     */
    private Integer state;

}
