package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.model.ParameterBatchDto;
import org.forbes.dal.entity.ProductParameter;

/**
 * @author lzw
 * @date 2020/1/9 15:03
 */
public interface IProductParameterSerivce extends IService<ProductParameter> {

    /***
     * 方法概述:批量添加参数
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2020/1/10 9:31
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void  batchAdd(ParameterBatchDto parameterBatchDto);
}
