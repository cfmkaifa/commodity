package org.forbes.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.IProductClassifyService;
import org.forbes.biz.IProductParameterSerivce;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.ClassifyStausEnum;
import org.forbes.comm.model.*;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.config.exception.ForbesException;
import org.forbes.dal.entity.ProductClassify;
import org.forbes.dal.entity.ProductParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author lzw
 * @date 2020/1/9 15:50
 */
@RestController
@RequestMapping("/parameter")
@Api(tags={"商品参数管理"})
@Slf4j
public class ProductParameterController {

    @Autowired
    IProductParameterSerivce productParameterSerivce;

    @Autowired
    private IProductClassifyService productClassifyService;

    /***
     * page方法概述:
     * @param basePageDto, parameterPageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.forbes.dal.entity.ProductParameter>>
     * @创建人 Tom
     * @创建时间 2020/1/10 9:14
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiOperation("分页查询参数")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.PARAMETER_PAGE_MSG),
            @ApiResponse(code=500,message = Result.PARAMETER_PAGE_ERROR)
    })
    public Result<IPage<ProductParameter>> page(BasePageDto basePageDto, ParameterPageDto parameterPageDto){
        Result<IPage<ProductParameter>> result=new Result<>();
        QueryWrapper<ProductParameter> qw=new QueryWrapper<>();
        if(ConvertUtils.isNotEmpty(parameterPageDto)){
            if(ConvertUtils.isNotEmpty(parameterPageDto.getName())){
                qw.like(PermsCommonConstant.PARAMETER_NAME,parameterPageDto.getName());
            }
        }
        IPage<ProductParameter> page=new Page<>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ProductParameter> parameterIPage=productParameterSerivce.page(page,qw);
        result.setResult(parameterIPage);
        return result;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation("批量添加参数")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.ADD_PARAMETER_MSG),
            @ApiResponse(code = 500,message = Result.ADD_PARAMETER_ERROR)
    })
    public Result<ParameterBatchDto> add(@RequestBody @Validated(value = SaveValid.class) ParameterBatchDto parameterBatchDto){
        Result<ParameterBatchDto> result=new Result<ParameterBatchDto>();
        ProductClassify productClassify=productClassifyService.getById(parameterBatchDto.getClassifyId());
        if (ConvertUtils.isEmpty(productClassify)){
            result.setBizCode(BizResultEnum.PRO_SPEC_CLASSFY_ID_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRO_SPEC_CLASSFY_ID_EXIST.getBizFormateMessage(),parameterBatchDto.getClassifyId()));
            return result;
        }
        try {
            productParameterSerivce.batchAdd(parameterBatchDto);
        }catch (ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
            return result;
        }

        result.setResult(parameterBatchDto);
        return result;
    }

    /***
     * deleProSpec方法概述:批量删除参数
     * @param ids
     * @return org.forbes.comm.vo.Result<java.lang.Boolean>
     * @创建人 Tom
     * @创建时间 2020/1/10 9:39
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/del-batch",method = RequestMethod.DELETE)
    @ApiOperation("批量删除参数")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.DEL_PARAMETER_MSG),
            @ApiResponse(code=500,message = Result.DEL_PARAMETER_ERROR)
    })
    public Result<Boolean> deleProSpec(@RequestParam(name = "ids",required = true) String ids){
        Result<Boolean> result = new Result<Boolean>();
        try {
            productParameterSerivce.removeByIds(Arrays.asList(ids.split(CommonConstant.SEPARATOR)));
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }

    /***
     * update方法概述:
     * @param parameterDto
     * @return org.forbes.comm.vo.Result<org.forbes.comm.model.ParameterDto>
     * @创建人 Tom
     * @创建时间 2020/1/10 9:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ApiOperation("修改参数")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.UPD_PRO_SPEC),
            @ApiResponse(code=500,message = Result.UPD_PRO_SPEC_ERROR)
    })
    public Result<ParameterDto> update(@RequestBody @Validated(value = SaveValid.class) ParameterDto parameterDto){
        Result<ParameterDto> result=new Result<ParameterDto>();
        ProductParameter productParameter=productParameterSerivce.getById(parameterDto.getId());
        if(ConvertUtils.isEmpty(productParameter)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(String.format(BizResultEnum.ENTITY_EMPTY.getBizFormateMessage()));
            return result;
        }
        String partname=parameterDto.getName();
        if(!partname.equalsIgnoreCase(productParameter.getName())){
            int count=productParameterSerivce.count(new QueryWrapper<ProductParameter>().eq(PermsCommonConstant.PARAMETER_NAME,partname));
            //判断要添加名称是否已存在
            if(count>0){
                result.setBizCode(BizResultEnum.PRO_SPEC_NAME_EXIST.getBizCode());
                result.setMessage(String.format(BizResultEnum.PRO_SPEC_NAME_EXIST.getBizFormateMessage()));
                return result;
            }
        }
        productParameter.setOrderSorts(parameterDto.getOrderSorts());
        productParameter.setClassifyId(parameterDto.getClassifyId());
        productParameter.setName(parameterDto.getName());
        productParameterSerivce.updateById(productParameter);
        return result;
    }
}
