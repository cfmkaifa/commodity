package org.forbes.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.IProductClassifyService;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.ProductClassifyDto;
import org.forbes.comm.model.ProductClassifyPageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.ProductClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/11 11:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/clssify")
@Api(tags={"商品分类"})
@Slf4j
public class ProductClassifyController {

    @Autowired
    private IProductClassifyService productClassifyService;


    /***
     * selectProductClassifyList方法概述:
     * @param basePageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.forbes.dal.entity.ProductClassify>>
     * @创建人 xfx
     * @创建时间 2019/12/11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ApiOperation("分页查询商品分类")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=ProductClassifyPageDto.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.SELECT_CLASSIFY),
            @ApiResponse(code=200,response=Result.class, message = Result.SELECT_ERROR_CLASSIFY)
    })
    public Result<IPage<ProductClassify>> selectProductClassifyList(@RequestBody(required = false) BasePageDto<ProductClassifyPageDto> basePageDto){
        log.debug("=============="+ JSON.toJSONString(basePageDto));
        Result<IPage<ProductClassify>> result=new Result<IPage<ProductClassify>>();
        QueryWrapper<ProductClassify> qw=new QueryWrapper<>();
        if(ConvertUtils.isNotEmpty(basePageDto.getData())){
            if(ConvertUtils.isNotEmpty(basePageDto.getData().getLevel())){
                qw.eq(PermsCommonConstant.LEVEL,basePageDto.getData().getLevel());
            }
            if(ConvertUtils.isNotEmpty(basePageDto.getData().getName())){
                qw.like(PermsCommonConstant.CLASSIFY_NAME,basePageDto.getData().getName());
            }
        }
        IPage<ProductClassify> page=new Page<>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ProductClassify> productClassifies=productClassifyService.page(page,qw);
        result.setResult(productClassifies);
        log.debug("========返回值为=======："+JSON.toJSONString(productClassifies));
        return result;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加询商品分类")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=ProductClassifyDto.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_ERROR_CLASSIFY),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_CLASSIFY)
    })
    public Result<ProductClassifyDto> addProdutClassify(@RequestBody @Validated(value = SaveValid.class) ProductClassify productClassify){
        return null;
    }

}
