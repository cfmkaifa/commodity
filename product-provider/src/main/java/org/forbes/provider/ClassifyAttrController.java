package org.forbes.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.ISClasAttrService;
import org.forbes.biz.ISProductClassifyService;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.ClassAttrDto;
import org.forbes.comm.model.ClassAttrPageDto;
import org.forbes.comm.model.ClassifyAttributeDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.ClassifyAttribute;
import org.forbes.dal.entity.ProductClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName
 * @Description 分类属性
 * @Author
 * @Date 2019/12/13 10:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("/attr")
@Api(tags={"分类属性"})
@Slf4j
public class ClassifyAttrController {

    @Autowired
    private ISClasAttrService clasAttrService;

    @Autowired
    private ISProductClassifyService productClassifyService;

    /***
     * page方法概述:
     * @param basePageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.forbes.dal.entity.ClassifyAttribute>>
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiOperation("分页查询分类属性")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.SELECT_CLASSIFY),
            @ApiResponse(code=200,message = Result.CLASSIF_ATTR_ERROR)
    })
    public Result<IPage<ClassifyAttribute>> page(BasePageDto<ClassAttrPageDto> basePageDto){
        log.debug("=============basePageDto:"+basePageDto);
        Result<IPage<ClassifyAttribute>> result=new Result<IPage<ClassifyAttribute>>();
        QueryWrapper<ClassifyAttribute> qw=new QueryWrapper<>();
        if(ConvertUtils.isNotEmpty(basePageDto.getData())){
            if(ConvertUtils.isNotEmpty(basePageDto.getData().getName())){
                qw.like(PermsCommonConstant.ATTR_NAME,basePageDto.getData().getName());
            }
        }
        IPage<ClassifyAttribute> page=new Page<>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ClassifyAttribute> clasAttributes=clasAttrService.page(page,qw);
        result.setResult(clasAttributes);
        log.debug("========返回值为=======："+ JSON.toJSONString(clasAttributes));
        return result;
    }

    /***
     * batchAdd方法概述:批量添加商品分类
     * @param classAttrDto
     * @return org.forbes.comm.vo.Result<org.forbes.comm.model.ClassAttrDto>
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/batch-add",method = RequestMethod.POST)
    @ApiOperation("批量添加商品分类")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.BATCH_ADD_ERROR_MSG),
            @ApiResponse(code=200,message = Result.BATCH_ADD_MSG)
    })
    public Result<ClassAttrDto> batchAdd(@RequestBody @Validated(value = SaveValid.class)ClassAttrDto classAttrDto){
        Result<ClassAttrDto> result=new Result<ClassAttrDto>();
        ProductClassify productClassify=productClassifyService.getById(classAttrDto.getClassifyId());
        if(ConvertUtils.isEmpty(classAttrDto)){
            result.setBizCode(BizResultEnum.BATCH_ADD_ERROR.getBizCode());
            result.setMessage(String.format(BizResultEnum.BATCH_ADD_ERROR.getBizFormateMessage(),productClassify.getName()));
            return result;
        }
        result.setResult(classAttrDto);
        clasAttrService.batchAdd(classAttrDto);
        return result;
    }


    /***
     * updClassAttr方法概述:修改分类属性
     * @param classifyAttributeDto
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ClassifyAttribute>
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/upd-attr",method = RequestMethod.PUT)
    @ApiOperation("修改分类属性")
    @ApiResponses(value = {
            @ApiResponse(code = 500,message =Result.UPD_ATTR_ERROR_MSG),
            @ApiResponse(code=200,message = Result.UPD_ATTR_MSG)
    })
    public Result<ClassifyAttribute> updClassAttr(@RequestBody @Validated(value = SaveValid.class)ClassifyAttributeDto classifyAttributeDto){
        Result<ClassifyAttribute> result=new Result<ClassifyAttribute>();
        ClassifyAttribute classifyAttribute=clasAttrService.getById(classifyAttributeDto.getId());
        if(ConvertUtils.isEmpty(classifyAttribute)){
            result.setBizCode(BizResultEnum.BATCH_ADD_ERROR.getBizCode());
            result.setMessage(String.format(BizResultEnum.BATCH_ADD_ERROR.getBizFormateMessage()));
            return result;
        }
        classifyAttribute.setName(classifyAttributeDto.getName());
        clasAttrService.updateById(classifyAttribute);
        result.setResult(classifyAttribute);
        return result;
    }


    /***
     * delAttr方法概述:删除分类属性
     * @param id
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ClassifyAttribute>
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/del-attr/{id}",method = RequestMethod.DELETE)
    @ApiOperation("删除分类属性")
    @ApiResponses(value = {
            @ApiResponse(code = 500,message =Result.DEL_ATTR_ERROR_MSG),
            @ApiResponse(code=200,message = Result.DEL_ATTR_MSG)
    })
    public Result<ClassifyAttribute> delAttr(@PathVariable(name = "id")Long id){
        Result<ClassifyAttribute> result=new Result<ClassifyAttribute>();
        ClassifyAttribute classifyAttribute=clasAttrService.getById(id);
        if(ConvertUtils.isEmpty(classifyAttribute)){
            result.setBizCode(BizResultEnum.BATCH_ADD_ERROR.getBizCode());
            result.setMessage(BizResultEnum.BATCH_ADD_ERROR.getBizMessage());
            return result;
        }
        clasAttrService.removeById(id);
        return result;
    }
}
