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
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.ProductClassifyDto;
import org.forbes.comm.model.ProductClassifyPageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.ProductClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



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
public class GoodsClassifyController {

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


    /***
     * addProdutClassify方法概述:
     * @param productClassify
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductClassify>
     * @创建人 xfx
     * @创建时间 2019/12/11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加商品分类")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=ProductClassify.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_ERROR_CLASSIFY),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_CLASSIFY)
    })
    public Result<ProductClassify> addProdutClassify(@RequestBody @Validated(value = SaveValid.class) ProductClassify productClassify){
        log.debug("============productClassify:"+JSON.toJSONString(productClassify));
        Result<ProductClassify> result=new Result<ProductClassify>();
        String name=productClassify.getName();//获取商品分类名称
        int existcount=productClassifyService.count(new QueryWrapper<ProductClassify>().eq(PermsCommonConstant.NAME,name));
        if(existcount>0){//判断商品分类名称是否重复
            result.setBizCode(BizResultEnum.PRODUCT_CLASSIFY_EXIST.getBizCode());
            result.success(String.format(BizResultEnum.PRODUCT_CLASSIFY_EXIST.getBizMessage(),name));
            return result;
        }else {
            Long parentId=productClassify.getParentId();
            if(parentId==null){//添加的是一级分类
                productClassify.setParentId(0L);
            }
            productClassify.setState(0L);
            productClassifyService.save(productClassify);
        }
        result.setResult(productClassify);
        log.debug("========productClassify:"+JSON.toJSONString(productClassify));
        return result;
    }

    /***
     * deleteProductClassify方法概述:
     * @param id
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductClassify>
     * @创建人 xfx
     * @创建时间 2019/12/11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("删除商品分类")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=ProductClassifyDto.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DEL_ERROR_CLASSIFY),
            @ApiResponse(code=200,response=Result.class, message = Result.DEL_CLASSIFY)
    })
    public Result<ProductClassify> deleteProductClassify(@RequestParam(name = "id",required =true)String id){
        Result<ProductClassify> result=new Result<ProductClassify>();
        ProductClassify productClassify=productClassifyService.getById(id);
        if(ConvertUtils.isNotEmpty(productClassify)){
            result.setBizCode(BizResultEnum.PRODUCT_CLASSIFY_NOT_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRODUCT_CLASSIFY_NOT_EXIST.getBizFormateMessage(),productClassify.getName()));
            return result;
        }
        //判断是否含有子级分类
        int childCount=productClassifyService.count(new QueryWrapper<ProductClassify>().eq(PermsCommonConstant.PARENT_ID,id));
        if(childCount>0){
            result.setBizCode(BizResultEnum.PRODUCT_CHILD_EXISTS.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRODUCT_CHILD_EXISTS.getBizFormateMessage(),productClassify.getName()));
            return result;
        }
        productClassifyService.removeById(id);
        return result;
    }
}
