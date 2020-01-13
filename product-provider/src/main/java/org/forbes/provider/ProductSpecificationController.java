package org.forbes.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.IProSpecficService;
import org.forbes.biz.IProductClassifyService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.ClassifyStausEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.ProSpecBatchDto;
import org.forbes.comm.model.ProSpecficDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.config.exception.ForbesException;
import org.forbes.dal.entity.ProductClassify;
import org.forbes.dal.entity.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/14 10:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/spec")
@Api(tags={"规格"})
@Slf4j
public class ProductSpecificationController {

    @Autowired
    private IProSpecficService proSpecficService;

    @Autowired
    private IProductClassifyService productClassifyService;


    /***
     * page方法概述:
     * @param basePageDto, proSpecficDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.forbes.dal.entity.ProductSpecification>>
     * @创建人 Tom
     * @创建时间 2020/1/13 14:15
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiOperation("分页查询规格")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.PAGE_PRO_SPEC),
            @ApiResponse(code=500,message = Result.PAGE_PRO_SPEC_ERROR)
    })
    public Result<IPage<ProductSpecification>> page(BasePageDto basePageDto,ProSpecficDto proSpecficDto){
        log.debug("================basePageDto:"+basePageDto);
        Result<IPage<ProductSpecification>> result=new Result<>();
        QueryWrapper<ProductSpecification> qw=new QueryWrapper<>();
        if(ConvertUtils.isNotEmpty(proSpecficDto)){
            if(ConvertUtils.isNotEmpty(proSpecficDto.getName())){
                qw.like(PermsCommonConstant.PRO_SPEC_NAME,proSpecficDto.getName());
            }
            if(ClassifyStausEnum.existsClassifyStausEnum(String.valueOf(proSpecficDto.getState()))){
                qw.eq(PermsCommonConstant.PRO_SPEC_STATE,proSpecficDto.getState());
            }
            if(ConvertUtils.isNotEmpty(proSpecficDto.getId())){
                qw.eq(PermsCommonConstant.ID,proSpecficDto.getId());
            }
        }
        IPage<ProductSpecification> page=new Page<>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ProductSpecification> productSPsecs=proSpecficService.page(page,qw);
        result.setResult(productSPsecs);
        return result;
    }

    /***
     * add方法概述:
     * @param proSpecBatchDto
     * @return org.forbes.comm.vo.Result<org.forbes.comm.model.ProSpecBatchDto>
     * @创建人 Tom
     * @创建时间 2020/1/13 14:15
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation("批量添加规格")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.ADD_PAGE_PRO_SPEC),
            @ApiResponse(code = 500,message = Result.ADD_PAGE_PRO_SPEC_ERROR)
    })
    public Result<ProSpecBatchDto> add(@RequestBody @Validated(value = SaveValid.class) ProSpecBatchDto proSpecBatchDto){
        Result<ProSpecBatchDto> result=new Result<ProSpecBatchDto>();
        ProductClassify productClassify=productClassifyService.getById(proSpecBatchDto.getClassifyId());
        if (ConvertUtils.isEmpty(productClassify)){
            result.setBizCode(BizResultEnum.PRO_SPEC_CLASSFY_ID_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRO_SPEC_CLASSFY_ID_EXIST.getBizFormateMessage(),proSpecBatchDto.getClassifyId()));
            return result;
        }
        try {
            proSpecficService.batchAdd(proSpecBatchDto);
        }catch (ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
            return result;
        }

        result.setResult(proSpecBatchDto);
        return result;
    }

    /***
     * deleProSpec方法概述:
     * @param ids
     * @return org.forbes.comm.vo.Result<java.lang.Boolean>
     * @创建人 Tom
     * @创建时间 2020/1/13 14:16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/del-batch",method = RequestMethod.DELETE)
    @ApiOperation("批量删除规格")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.DEL_PRO_SPEC),
            @ApiResponse(code=500,message = Result.DEL_PRO_SPEC_ERROR)
    })
    public Result<Boolean> deleProSpec(@RequestParam(name = "ids",required = true) String ids){
        Result<Boolean> result = new Result<Boolean>();
        try {
            proSpecficService.removeByIds(Arrays.asList(ids.split(CommonConstant.SEPARATOR)));
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }

    /***
     * update方法概述:
     * @param proSpecficDto
     * @return org.forbes.comm.vo.Result<org.forbes.comm.model.ProSpecficDto>
     * @创建人 Tom
     * @创建时间 2020/1/13 14:16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ApiOperation("修改规格")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.UPD_PRO_SPEC),
            @ApiResponse(code=500,message = Result.UPD_PRO_SPEC_ERROR)
    })
    public Result<ProSpecficDto> update(@RequestBody @Validated(value = SaveValid.class) ProSpecficDto proSpecficDto){
        Result<ProSpecficDto> result=new Result<ProSpecficDto>();
        ProductSpecification productSpecification=proSpecficService.getById(proSpecficDto.getId());
        if(ConvertUtils.isEmpty(productSpecification)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(String.format(BizResultEnum.ENTITY_EMPTY.getBizFormateMessage()));
            return result;
        }
        String prospectname=proSpecficDto.getName();
        if(!prospectname.equalsIgnoreCase(productSpecification.getName())){
            int count=proSpecficService.count(new QueryWrapper<ProductSpecification>().eq(PermsCommonConstant.PRO_SPEC_NAME,prospectname));
            //判断要添加名称是否已存在
            if(count>0){
                result.setBizCode(BizResultEnum.PRO_SPEC_NAME_EXIST.getBizCode());
                result.setMessage(String.format(BizResultEnum.PRO_SPEC_NAME_EXIST.getBizFormateMessage()));
                return result;
            }
        }
        productSpecification.setOrderSorts(proSpecficDto.getOrderSorts());
        productSpecification.setState(proSpecficDto.getState());
        productSpecification.setClassifyId(proSpecficDto.getClassifyId());
        productSpecification.setName(proSpecficDto.getName());
        proSpecficService.updateById(productSpecification);
        return result;
    }
}
