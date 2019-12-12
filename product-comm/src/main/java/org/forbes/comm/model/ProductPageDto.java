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

/**
 * @author lzw
 * @date 2019/12/11 15:56
 */
@Data
@ApiModel(description="商品查询参数")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductPageDto implements Serializable{

    private static final long serialVersionUID = 6314536822829481483L;
    /**
     * 1上架2未上架3待审核4审核失败
     * Table:     f_product
     * Column:    state
     * Nullable:  true
     */
    private Integer state;

    /**
     * 商品编码
     * Table:     f_product
     * Column:    pro_sn
     * Nullable:  true
     */
    private String proSn;
}
