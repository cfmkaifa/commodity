package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.dal.entity.ProductSpecification;

public interface IProSpecificService extends IService<ProductSpecification> {
    /***
     * 方法概述: 删除规格
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     *
     */
    boolean deleteProductSpecification(Long id);
}
