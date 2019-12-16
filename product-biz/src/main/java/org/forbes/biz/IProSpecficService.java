package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.model.ClassAttrDto;
import org.forbes.comm.model.ProSpecBatchDto;
import org.forbes.dal.entity.ProductSpecification;

public interface IProSpecficService extends IService<ProductSpecification>{

    /***
     * 方法概述:批量添加规格
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/14
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void  batchAdd(ProSpecBatchDto proSpecBatchDto);
}
