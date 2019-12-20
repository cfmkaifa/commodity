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
import org.forbes.biz.IClasAttrService;
import org.forbes.biz.IProductClassifyService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.ClassAttrDto;
import org.forbes.comm.model.ClassAttrPageDto;
import org.forbes.comm.model.ClassifyAttributeDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.config.exception.ForbesException;
import org.forbes.dal.entity.ClassifyAttribute;
import org.forbes.dal.entity.ProductClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
public class ClassifyAttributeController {

    @Autowired
    private IClasAttrService clasAttrService;

    @Autowired
    private IProductClassifyService productClassifyService;

    /***
     * page方法概述:分页查询分类属性
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
    public Result<IPage<ClassifyAttribute>> page( BasePageDto basePageDto,ClassAttrPageDto classAttrPageDto){
        log.debug("=============basePageDto:"+basePageDto);
        Result<IPage<ClassifyAttribute>> result=new Result<IPage<ClassifyAttribute>>();
        QueryWrapper<ClassifyAttribute> qw=new QueryWrapper<>();
        if(ConvertUtils.isNotEmpty(classAttrPageDto)){
            if(ConvertUtils.isNotEmpty(classAttrPageDto.getName())){
                qw.like(PermsCommonConstant.ATTR_NAME,classAttrPageDto.getName());
            }
            if(ConvertUtils.isNotEmpty(classAttrPageDto.getId())){
                qw.eq(PermsCommonConstant.ID,classAttrPageDto.getId());
            }
        }
        IPage<ClassifyAttribute> page=new Page<>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ClassifyAttribute> clasAttributes=clasAttrService.page(page,qw);
        result.setResult(clasAttributes);
        return result;
    }

    /***
     * batchAdd方法概述:批量添加商品分类属性
     * @param classAttrDto
     * @return org.forbes.comm.vo.Result<org.forbes.comm.model.ClassAttrDto>
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/batch-add",method = RequestMethod.POST)
    @ApiOperation("批量添加分类属性")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.BATCH_ADD_ERROR_MSG),
            @ApiResponse(code=200,message = Result.BATCH_ADD_MSG)
    })
    public Result<ClassAttrDto> batchAdd(@RequestBody @Validated(value = SaveValid.class) ClassAttrDto classAttrDto){
        log.debug("=============classAttrDto:"+JSON.toJSONString(classAttrDto));
        Result<ClassAttrDto> result=new Result<ClassAttrDto>();
        ProductClassify productClassify=productClassifyService.getById(classAttrDto.getClassifyId());
        if(ConvertUtils.isEmpty(classAttrDto)){
            result.setBizCode(BizResultEnum.BATCH_ADD_ERROR.getBizCode());
            result.setMessage(String.format(BizResultEnum.BATCH_ADD_ERROR.getBizFormateMessage(),productClassify.getName()));
            return result;
        }
        try {
            clasAttrService.batchAdd(classAttrDto);
        }catch (ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
            return result;
        }
        result.setResult(classAttrDto);
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
    public Result<ClassifyAttribute> updClassAttr(@RequestBody @Validated(value = UpdateValid.class) ClassifyAttributeDto classifyAttributeDto){
        log.debug("=============classifyAttributeDto:"+JSON.toJSONString(classifyAttributeDto));
        Result<ClassifyAttribute> result=new Result<ClassifyAttribute>();
        ClassifyAttribute classifyAttribute=clasAttrService.getById(classifyAttributeDto.getId());
        if(ConvertUtils.isEmpty(classifyAttribute)){
            result.setBizCode(BizResultEnum.BATCH_ADD_ERROR.getBizCode());
            result.setMessage(String.format(BizResultEnum.BATCH_ADD_ERROR.getBizFormateMessage()));
            return result;
        }
        String attrname=classifyAttributeDto.getName();
        //判断属性名称是否已存在
        if(!attrname.equalsIgnoreCase(classifyAttribute.getName())){
            int attrcount=clasAttrService.count(new QueryWrapper<ClassifyAttribute>().eq(PermsCommonConstant.ATTR_NAME,attrname));
            if(attrcount>0){
                result.setBizCode(BizResultEnum.REPETITION_ATTR.getBizCode());
                result.setMessage(String.format(BizResultEnum.REPETITION_ATTR.getBizFormateMessage()));
                return result;
            }
        }
        String attributeSn=classifyAttributeDto.getAttributeSn();
        if(!attributeSn.equalsIgnoreCase(classifyAttribute.getAttributeSn())){
            int attrcSnount=clasAttrService.count(new QueryWrapper<ClassifyAttribute>().eq(PermsCommonConstant.ATTRIBUTE_SN,attributeSn));
            if(attrcSnount>0){
                result.setBizCode(BizResultEnum.ATTRIBUTE_SN.getBizCode());
                result.setMessage(String.format(BizResultEnum.ATTRIBUTE_SN.getBizFormateMessage()));
                return result;
            }
        }
        classifyAttribute.setName(classifyAttributeDto.getName());
        classifyAttribute.setAttributeSn(classifyAttributeDto.getAttributeSn());
        clasAttrService.updateById(classifyAttribute);
        result.setResult(classifyAttribute);
        return result;
    }


    /***
     * delAttr方法概述:删除分类属性
     * @param ids
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ClassifyAttribute>
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/del-attr",method = RequestMethod.DELETE)
    @ApiOperation("批量删除分类属性")
    @ApiResponses(value = {
            @ApiResponse(code = 500,message =Result.DEL_ATTR_ERROR_MSG),
            @ApiResponse(code=200,message = Result.DEL_ATTR_MSG)
    })
    public Result<Boolean> delAttr(@RequestParam(name = "ids",required = true) String ids){
        Result<Boolean> result = new Result<Boolean>();
        try {
            clasAttrService.removeByIds(Arrays.asList(ids.split(CommonConstant.SEPARATOR)));
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }


    @RequestMapping(value = "/check",method = RequestMethod.GET)
    @ApiOperation("校验分类属性编码")
    @ApiResponses(value = {
            @ApiResponse(code = 500,message =Result.CHECK_ATTR_SN_ERROR),
            @ApiResponse(code=200,message = Result.CHECK_ATTR_SN)
    })
    public Result<Boolean>  check(@RequestParam(value = "attributeSn",required = true)String attributeSn){
        Result<Boolean> result=new Result<Boolean>();
        int count=clasAttrService.count(new QueryWrapper<ClassifyAttribute>().eq(PermsCommonConstant.ATTRIBUTE_SN,attributeSn));
        if(count>0){
            result.setBizCode(BizResultEnum.ATTRIBUTE_SN.getBizCode());
            result.setMessage(BizResultEnum.ATTRIBUTE_SN.getBizMessage());
            result.setResult(false);
            return result;
        }
        result.setResult(true);
        return result;
    }
}
