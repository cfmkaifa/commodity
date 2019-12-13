package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.model.ClassifyAttributeDto;
import org.forbes.comm.model.ProductClassifyDto;
import org.forbes.dal.entity.ProductClassify;

public interface ISProductClassifyService extends IService<ProductClassify>{

    
    /***
     * 方法概述: 添加商品分类+添加分类属性+添加规格
     * @param productClassifyDto
     * @return 
     * @创建人 xfx
     * @创建时间 2019/12/12
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addClassify(ProductClassifyDto productClassifyDto);

}
