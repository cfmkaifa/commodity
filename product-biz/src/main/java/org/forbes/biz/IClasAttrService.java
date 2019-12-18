package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.model.ClassAttrDto;
import org.forbes.dal.entity.ClassifyAttribute;

public interface IClasAttrService extends IService<ClassifyAttribute> {


    /***
     * 方法概述:批量添加分类属性
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void batchAdd(ClassAttrDto classAttrDto);

    /***
     * 方法概述: 删除属性
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     *
     */
     boolean deleteAttrbute(Long id);
}
