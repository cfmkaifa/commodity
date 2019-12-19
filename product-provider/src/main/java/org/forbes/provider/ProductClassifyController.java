package org.forbes.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.IClasAttrService;
import org.forbes.biz.IProSpecificService;
import org.forbes.biz.IProductClassifyService;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.ClassifyStausEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.*;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.ClassifyAttribute;
import org.forbes.dal.entity.ProductClassify;
import org.forbes.dal.entity.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private IClasAttrService classifyAttrService;

    @Autowired
    private IProSpecificService proSpecificService;



    /***
     * selectProductClassifyList方法概述:分页查询商品分类
     * @param basePageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.forbes.dal.entity.ProductClassify>>
     * @创建人 xfx
     * @创建时间 2019/12/11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ApiOperation("分页查询商品分类")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.SELECT_CLASSIFY),
            @ApiResponse(code=200,message = Result.SELECT_ERROR_CLASSIFY)
    })
    public Result<IPage<ProductClassify>> page(BasePageDto basePageDto,ProductClassifyPageDto productClassifyPageDto){
        log.debug("=============="+ JSON.toJSONString(basePageDto));
        Result<IPage<ProductClassify>> result=new Result<IPage<ProductClassify>>();
        QueryWrapper<ProductClassify> qw=new QueryWrapper<>();
        if(ConvertUtils.isNotEmpty(productClassifyPageDto)){
            if(ConvertUtils.isNotEmpty(productClassifyPageDto.getLevel())){
                qw.eq(PermsCommonConstant.LEVEL,productClassifyPageDto.getLevel());
            }
            if(ConvertUtils.isNotEmpty(productClassifyPageDto.getName())){
                qw.like(PermsCommonConstant.CLASSIFY_NAME,productClassifyPageDto.getName());
            }
        }
        IPage<ProductClassify> page=new Page<>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ProductClassify> productClassifies=productClassifyService.page(page,qw);
        result.setResult(productClassifies);
        return result;
    }

    /***
     * addProdutClassify方法概述:添加商品分类
     * @param productClassifyDto
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductClassify>
     * @创建人 xfx
     * @创建时间 2019/12/11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加商品分类")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_ERROR_CLASSIFY),
            @ApiResponse(code=200,message = Result.ADD_CLASSIFY)
    })
    public Result<ProductClassifyDto> addGoodsClassify( @RequestBody @Validated(value = SaveValid.class)ProductClassifyDto productClassifyDto){
        log.debug("============productClassifyDto:"+JSON.toJSONString(productClassifyDto));
        Result<ProductClassifyDto> result=new Result<ProductClassifyDto>();
        String name=productClassifyDto.getName();//获取商品分类名称
        int existcount=productClassifyService.count(new QueryWrapper<ProductClassify>().eq(PermsCommonConstant.NAME,name));
        if(existcount>0){//
            result.setBizCode(BizResultEnum.PRODUCT_CLASSIFY_EXIST.getBizCode());
            result.success(String.format(BizResultEnum.PRODUCT_CLASSIFY_EXIST.getBizMessage(),name));
            return result;
        }
        int classifyCount=productClassifyService.count(new QueryWrapper<ProductClassify>().eq(PermsCommonConstant.CLASSIFY_SN,productClassifyDto.getClassifySn()));
        if(classifyCount>0){//判断商品分类编码是否已存在
            result.setBizCode(BizResultEnum.CLASSIFY_SN_EXIST.getBizCode());
            result.success(String.format(BizResultEnum.CLASSIFY_SN_EXIST.getBizMessage(),name));
            return result;
        }
        try {
            productClassifyService.addClassify(productClassifyDto);
        }catch (ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
            return result;
        }
        return result;
    }

    /***
     * deleteProductClassify方法概述: 根据商品分类id删除
     * @param
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductClassify>
     * @创建人 xfx
     * @创建时间 2019/12/11
     * @修改人 (修改了该文件,请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("删除商品分类")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=ProductClassify.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DEL_ERROR_CLASSIFY),
            @ApiResponse(code=200,message = Result.DEL_CLASSIFY)
    })
    public Result<ProductClassify> delGoodsClassify(@RequestParam(name = "id",required =true)Long id){
        log.debug("==================id:"+JSON.toJSONString(id));
        Result<ProductClassify> result=new Result<ProductClassify>();
        ProductClassify productClassify=productClassifyService.getById(id);
        if(ConvertUtils.isEmpty(productClassify)){
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
        //判断分类状态，启用状态时不可以删除下面分类
        String state=productClassify.getState();
        boolean res=ClassifyStausEnum.NORMAL.getCode().equals(state);
        if(res){//启用状态
            result.setBizCode(BizResultEnum.PRO_CLASSIFY_STATE.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRO_CLASSIFY_STATE.getBizFormateMessage(),productClassify.getName()));
            return result;
        }
        //禁用状态，可删除相关分类和规格
        try {
            classifyAttrService.deleteAttrbute(id);
            proSpecificService.deleteProductSpecification(id);
            productClassifyService.removeById(id);
        }catch (ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
            return result;
        }
            return result;
    }

    /***
     * update方法概述: 修改商品分类状态
     * @param id,state
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductClassify>
     * @创建人 xfx
     * @创建时间 2019/12/11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */

    @RequestMapping(value = "/uppate-status/{id}",method = RequestMethod.PUT)
    @ApiOperation("禁用/启用商品分类状态")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.UP_CLASSIFY_ERROR_STATUS),
            @ApiResponse(code=200,response = ProductClassify.class,message = Result.UP_CLASSIFY_STATUS)
    })
    public Result<ProductClassify> update(@PathVariable Long id,@RequestParam(value="state",required=true)String state){
        log.debug("==================id:"+JSON.toJSONString(id)+"============state:"+JSON.toJSONString(state));
        Result<ProductClassify> result=new Result<ProductClassify>();
        boolean isClassifyres= ClassifyStausEnum.existsClassifyStausEnum(state);
        if(!isClassifyres){
            result.setBizCode(BizResultEnum.CLASSIFY_STATUS_NOT_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.CLASSIFY_STATUS_NOT_EXIST.getBizFormateMessage(),state));
            return result;
        }
        ProductClassify productClassify=productClassifyService.getById(id);
        if(!ConvertUtils.isNotEmpty(productClassify)){
            result.setBizCode(BizResultEnum.PRODUCT_CLASSIFY_NOT_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRODUCT_CLASSIFY_NOT_EXIST.getBizFormateMessage(),productClassify.getName()));
            return result;
        }
        productClassify.setState(state);
        productClassifyService.updateById(productClassify);
        return result;
    }



    /***
     * 方法概述:编辑商品分类
     * @param productClassify
     * @return ProductClassify
     * @创建人 xfx
     * @创建时间 2019/12/12
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ApiOperation("编辑商品分类")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.UP_CLASSIFY_ERROR_NAME),
            @ApiResponse(code=200,message = Result.UP_CLASSIFY_NAME)
    })
    public Result<ProductClassify> editProductClassify(@RequestBody  @Validated(value = UpdateValid.class) ProductClassify productClassify){
        log.debug("============productClassify:"+JSON.toJSONString(productClassify));
        Result<ProductClassify> result=new Result<ProductClassify>();
        ProductClassify product=productClassifyService.getById(productClassify.getId());
        String name=productClassify.getName();
        if(!name.equalsIgnoreCase(product.getName())){
            int  existcount=productClassifyService.count(new QueryWrapper<ProductClassify>().eq(PermsCommonConstant.NAME,name));
            if(existcount>0){//判断商品分类名称是否重复
                result.setBizCode(BizResultEnum.PRODUCT_CLASSIFY_EXIST.getBizCode());
                result.success(String.format(BizResultEnum.PRODUCT_CLASSIFY_EXIST.getBizMessage(),name));
                return result;
            }
        }
        String classify=productClassify.getClassifySn();
        if(!classify.equalsIgnoreCase(product.getClassifySn())){
            int classifycount= productClassifyService.count(new QueryWrapper<ProductClassify>().eq(PermsCommonConstant.CLASSIFY_SN,classify));
            if(classifycount>0){//判断商品分类编码是否重复
                result.setBizCode(BizResultEnum.CLASSIFY_SN_EXIST.getBizCode());
                result.success(String.format(BizResultEnum.CLASSIFY_SN_EXIST.getBizMessage(),name));
                return result;
            }
        }
        productClassify.setName(name);
        productClassifyService.updateById(productClassify);
        log.debug("============productClassify:"+JSON.toJSONString(productClassify));
        result.setResult(productClassify);
        return result;
    }

    /***
     * getClasAttr方法概述:根据商品分类id查询分类属性
     * @param id
     *
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ClassifyAttribute>
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/getclass-attr/{id}",method =RequestMethod.GET )
    @ApiOperation("根据商品分类id查询分类属性")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.CLASSIF_ATTR_ERROR),
            @ApiResponse(code=200,message = Result.CLASSIF_ATTR)
    })
    public Result<List<ClassifyAttribute>> getClasAttr(@PathVariable Long id){
        log.debug("============id:"+JSON.toJSONString(id));
        Result<List<ClassifyAttribute>> result=new Result<List<ClassifyAttribute>>();
        if(ConvertUtils.isEmpty(id)){
            result.setBizCode(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_EXIST.getBizFormateMessage(),id));
            return result;
        }
        List<ClassifyAttribute> classifyAttributes=classifyAttrService.list(new QueryWrapper<ClassifyAttribute>().eq(PermsCommonConstant.CLASSIFY_ID,id));
        result.setResult(classifyAttributes);
        log.debug("=============classifyAttributes集合："+JSON.toJSONString(classifyAttributes));
        return result;
    }


    /***
     * getProSpec方法概述:根据商品分类id查询规格
     * @param id
     * @return org.forbes.comm.vo.Result<java.util.List<org.forbes.dal.entity.ProductSpecification>>
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/get-pro-spec/{id}",method =RequestMethod.GET )
    @ApiOperation("根据商品分类id查询规格")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.PRO_SPEC_ERROR),
            @ApiResponse(code=200,message = Result.PRO_SPEC)
    })
    public Result<List<ProductSpecification>> getProSpec(@PathVariable Long id){
        log.debug("============id:"+JSON.toJSONString(id));
        Result<List<ProductSpecification>> result=new Result<List<ProductSpecification>>();
        if(ConvertUtils.isEmpty(id)){
            result.setBizCode(BizResultEnum.PRO_SPEC_CLASSIFY_ID.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRO_SPEC_CLASSIFY_ID.getBizFormateMessage(),id));
            return result;
        }
        List<ProductSpecification> productSpecifications=proSpecificService.list(new QueryWrapper<ProductSpecification>().eq(PermsCommonConstant.PRO_SPEC_CLASSIFY_ID,id));
        result.setResult(productSpecifications);
        log.debug("=============ProductSpecification："+JSON.toJSONString(productSpecifications));
        return result;
    }


    @ApiOperation("根据父级分类id查询子级分类集合")
    @RequestMapping(value = "/get-child-classify/{id}",method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.CHILD_CLASSIFY_ERROR),
            @ApiResponse(code=200,message = Result.CHILD_CLASSIFY)
    })
    public Result<List<ProductClassify>> getChildClassify(@PathVariable Long id){
        Result<List<ProductClassify>> result=new Result<List<ProductClassify>>();
        ProductClassify productClassify=productClassifyService.getById(id);
        if(ConvertUtils.isEmpty(productClassify)){
            result.setBizCode(BizResultEnum.PRODUCT_CLASSIFY_NOT_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRODUCT_CLASSIFY_NOT_EXIST.getBizFormateMessage(),id));
            return result;
        }
        List<ProductClassify> productClassifies=productClassifyService.list(new QueryWrapper<ProductClassify>().eq(PermsCommonConstant.PARENT_ID,id));
        result.setResult(productClassifies);
        return result;
    }

    @ApiOperation("校验分类编码是否唯一")
    @RequestMapping(value = "/unique",method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.CLASSIFY_CHECK_ERROR),
            @ApiResponse(code=200,message = Result.CLASSIFY_CHECK)
    })
    public Result<Boolean> check(@RequestParam(value = "classifySn")String classifySn){
        Result<Boolean> result=new Result<Boolean>();
        int count=productClassifyService.count(new QueryWrapper<ProductClassify>().eq(PermsCommonConstant.CLASSIFY_SN,classifySn));
        if(count>0){
            result.setBizCode(BizResultEnum.CLASSIFY_SN_EXIST.getBizCode());
            result.setMessage(BizResultEnum.CLASSIFY_SN_EXIST.getBizMessage());
            return result;
        }
        return result;
    }
}
