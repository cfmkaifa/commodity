package org.forbes.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.IProductService;
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
import org.forbes.dal.entity.AttributeValue;
import org.forbes.dal.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result<IPage<ProductVo>> PageProduct(BasePageDto basePageDto,ProductPageDto productPageDto){
        log.debug("=============="+ JSON.toJSONString(basePageDto));
        Result<IPage<ProductVo>> result=new Result<IPage<ProductVo>>();
        IPage<ProductVo> page = new Page<ProductVo>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ProductVo> pageUsers =  productService.PageProduct(page, productPageDto);
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
        Product oldSysRole = productService.getById(productDto.getId());
        if(ConvertUtils.isEmpty(oldSysRole)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        String procn = productDto.getProSn();
        //判断当前商家编码与输入的是否一致
        if (!procn.equalsIgnoreCase(oldSysRole.getProSn())) {
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
    @RequestMapping(value = "/delete-product", method = RequestMethod.DELETE)
    @ApiOperation("删除商品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="id",value = "商品ID",required = true)
    })
    public Result<Product> deleteProduct(@RequestParam(name="id",required=true) String id) {
        Result<Product> result = new Result<Product>();
        Product product = productService.getById(id);
        if(ConvertUtils.isEmpty(product)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        productService.removeById(id);
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
        if(isUserStatus){
            result.setBizCode(BizResultEnum.PRODUCT_STATUS_NO_EXISTS.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRODUCT_STATUS_NO_EXISTS.getBizFormateMessage(), state));
            return result;
        }
        if(ConvertUtils.isEmpty(product)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
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
           result.setResult(product);
           return result;
    }



}
