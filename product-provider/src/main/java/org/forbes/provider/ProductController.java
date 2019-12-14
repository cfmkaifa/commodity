package org.forbes.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.*;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.ProductStausEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.ProductDto;
import org.forbes.comm.model.ProductPageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ProductVo;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author lzw
 * @date 2019/12/11 15:34
 */
@RestController
@RequestMapping("/product")
@Api(tags={"商品管理"})
@Slf4j
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    IProductAttachService productAttachService;

    @Autowired
    IAttributeValueService attributeValueService;

    @Autowired
    IProductSpecificationService productSpecificationService;

    @Autowired
    ISpecificationValueService specificationValueService;

    /***
     * selectProduct方法概述:分页查询商品信息
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/11 15:45
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ApiOperation("商品分页查询")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.SELECT_PRODUCT),
            @ApiResponse(code=500, message = Result.SELECT_ERROR_PRODUCT)
    })
    public Result<IPage<ProductVo>> pageProduct(BasePageDto basePageDto,ProductPageDto productPageDto){
        log.debug("=============="+ JSON.toJSONString(basePageDto));
        Result<IPage<ProductVo>> result=new Result<IPage<ProductVo>>();
        IPage<ProductVo> page = new Page<ProductVo>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ProductVo> pageUsers =  productService.pageProduct(page, productPageDto);
        result.setResult(pageUsers);
        return result;
    }

    /***
     * addProduct方法概述:添加商品
     * @param productDto
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/11 17:03
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation("添加商品")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.ADD_PRODUCT),
            @ApiResponse(code=500,message = Result.ADD_ERROR_PRODUCT)
    })
    public Result<ProductDto> addProduct(@RequestBody @Validated(value = SaveValid.class)ProductDto productDto){
        log.debug("传入的参数为"+ JSON.toJSONString(productDto));
        Result<ProductDto> result=new Result<ProductDto>();
        ProductDto productDtos=new ProductDto();
        String procn = productDtos.getProSn();
        int existsCount = productService.count(new QueryWrapper<Product>().eq(DataColumnConstant.PROCN, procn));
        if(existsCount > 0 ) {//存在此记录
            result.setBizCode(BizResultEnum.PRODUCT_CODE_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRODUCT_CODE_EXIST.getBizFormateMessage(), procn));
            return result;
        }
        productService.addProduct(productDto);
        result.setResult(productDto);
        return result;
    }

    /***
     * updateProduct方法概述:修改商品
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/11 17:30
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ApiOperation("编辑")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.UPDATE_PRODUCT),
            @ApiResponse(code=500,message = Result.UPDATE_ERROR_PRODUCT)
    })
    public Result<ProductDto> updateProduct(@RequestBody @Validated(value=UpdateValid.class) ProductDto productDto){
        log.debug("传入的参数为"+JSON.toJSONString(productDto));
        Result<ProductDto> result=new Result<ProductDto>();
        Product oldProduct = productService.getById(productDto.getId());
        if(ConvertUtils.isEmpty(oldProduct)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        String procn = productDto.getProSn();
        //判断当前商家编码与输入的是否一致
        if (!procn.equalsIgnoreCase(oldProduct.getProSn())) {
            //查询是否和其他商家编码一致
            int existsCount = productService.count(new QueryWrapper<Product>().eq(DataColumnConstant.PROCN, procn));
            if (existsCount > 0) {//存在此记录
                result.setBizCode(BizResultEnum.PRODUCT_CODE_EXIST.getBizCode());
                result.setMessage(String.format(BizResultEnum.PRODUCT_CODE_EXIST.getBizFormateMessage(), procn));
                return result;
            }
        }
        productService.updateProduct(productDto);
        result.setResult(productDto);
        return result;
    }

    /***
     * deleteProduct方法概述:删除商品信息
     * @param 
     * @return 
     * @创建人 Tom
     * @创建时间 2019/12/11 18:11
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("删除商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="id",value = "商品ID",required = true)
    })
    public Result<Boolean> deleteProduct(@RequestParam(name="id",required=true) String id) {
        Result<Boolean> result = new Result<Boolean>();
        Product product = productService.getById(id);
        if(ConvertUtils.isEmpty(product)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        productService.removeById(id);
        return result;
    }

    /***批量删除商品
     * deleteBatch方法慨述:
     * @param ids
     * @return Result<Product>
     * @创建人 lzw
     * @创建时间 2019/12/14
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("批量删除商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids",value = "商品IDs",required = true)
    })
    @RequestMapping(value = "/delete-batch", method = RequestMethod.DELETE)
    public Result<Boolean> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        Result<Boolean> result = new Result<Boolean>();
        productService.removeByIds(Arrays.asList(ids.split(CommonConstant.SEPARATOR)));
        return result;
    }


    /***
     * updateProductState方法概述:
     * @param id, state
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.Product>
     * @创建人 Tom
     * @创建时间 2019/12/12 10:34
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-product-state",method = RequestMethod.PUT)
    @ApiOperation("上架/下架商品")
    @ApiImplicitParam(value="state",name="商品状态",required=false)
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG)
    })
    public Result<Product> updateProductState(@RequestParam(value="id",required=true)Long id,@RequestParam(value = "state",required = true)String state){
        Result<Product> result=new Result<Product>();
        boolean isUserStatus = ProductStausEnum.existsProductStausEnum(state);
        Product product = productService.getById(id);
        if(ConvertUtils.isEmpty(product)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        if(isUserStatus){
            result.setBizCode(BizResultEnum.PRODUCT_STATUS_NO_EXISTS.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRODUCT_STATUS_NO_EXISTS.getBizFormateMessage(), state));
            return result;
        }
        product.setState(state);
        productService.updateById(product);
        return result;
    }

    /***
     * selectProducts方法概述:
     * @param id
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.Product>
     * @创建人 Tom
     * @创建时间 2019/12/12 11:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-products", method = RequestMethod.GET)
    @ApiOperation("通过商品id查询商品信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "商品牌号")
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.SELECT_ERROR_PRODUCT),
            @ApiResponse(code=200,message = Result.SELECT_PRODUCT)
    })
    public Result<Product> selectProducts(@RequestParam(value = "id",required = true)Long id){
           Result<Product> result=new Result<Product>();
           Product product=productService.getById(id);

           productAttachService.getById(id);
           attributeValueService.getById(id);
           productSpecificationService.getById(id);
           specificationValueService.getById(id);
           result.setResult(product);
           return result;
    }


    /****
     * checkProductCode方法慨述:校验角色编码唯一
     * @param procn
     * @return Result<Boolean>
     * @创建人 lzw
     * @创建时间 2019年12月14日
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("校验商品编码唯一")
    @ApiImplicitParam(name = "procn" ,value = "商品编码")
    @RequestMapping(value = "/check-product-code", method = RequestMethod.GET)
    public Result<Boolean> checkProductCode(@RequestParam(value="procn",required=true)String procn) {
        Result<Boolean> result = new Result<Boolean>();
        int existsCount = productService.count(new QueryWrapper<Product>().eq(DataColumnConstant.PROCN, procn));
        if(existsCount > 0 ) {//存在此记录
            result.setBizCode(BizResultEnum.PRODUCT_CODE_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRODUCT_CODE_EXIST.getBizFormateMessage(), procn));
            result.setResult(false);
            return result;
        }
        return result;
    }

}
