package org.forbes.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.IProductSysService;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.ProductPageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ProductSysPageVo;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/16 16:12
 * @Version 1.0
 **/
@RestController
@RequestMapping("/sys-product")
@Api(tags={"商品管理"})
@Slf4j
public class SysProductController {


    @Autowired
    private IProductSysService productSysService;

    /***
     * check方法概述:总后台端审核商品
     * @param id, state
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.Product>
     * @创建人 xfx
     * @创建时间 2019/12/14
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/check",method = RequestMethod.PUT)
    @ApiOperation("总后台端审核商品")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.CHECK_GOOD),
            @ApiResponse(code = 500,message = Result.CHECK_GOOD_ERROR)
    })
    public Result<Product> check(@RequestParam(value="id",required = true)Long id, @RequestParam(value = "state",required = true)String state){
        log.debug("==============id:"+ JSON.toJSONString(id)+"==================state:"+JSON.toJSONString(state));
        Result<Product> result=new Result<Product>();
        Product product=productSysService.getById(id);
        if(ConvertUtils.isEmpty(product)){//判断商品是否存在
            result.setBizCode(BizResultEnum.PRO_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PRO_EXIST.getBizFormateMessage()));
            return result;
        }
        product.setState(state);
        productSysService.updateById(product);
        result.setResult(product);
        return result;
    }


    /***
     * sysPage方法概述:总后台分页查询商品
     * @param basePageDto, productPageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.forbes.comm.vo.ProductSysPageVo>>
     * @创建人 xfx
     * @创建时间 2019/12/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/sys-page",method = RequestMethod.GET)
    @ApiOperation("总后台分页查询商品")
    @ApiResponses(value = {
            @ApiResponse(code = 500,message =Result.SYS_GOOD_PAGE_ERROR),
            @ApiResponse(code = 200,message = Result.SYS_GOOD_PAGE)
    })
    public Result<IPage<ProductSysPageVo>>  sysPage(BasePageDto basePageDto, ProductPageDto productPageDto) {
        log.debug("==============basePageDto:" + JSON.toJSONString(basePageDto) + "==============ProductPageDto:" + JSON.toJSONString(productPageDto));
        Result<IPage<ProductSysPageVo>> result = new Result<>();
        IPage<ProductSysPageVo> page = new Page<>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ProductSysPageVo> productSysPageVoIPage = productSysService.sysPage(page, productPageDto);
        result.setResult(productSysPageVoIPage);
        return result;
    }


    /***
     * detail方法概述:商品详情
     * @param id
     * @return org.forbes.comm.vo.Result<org.forbes.comm.vo.ProductSysPageVo>
     * @创建人 xfx
     * @创建时间 2019/12/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("商品详情")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = Result.SYS_GOOD_DETAIL),
            @ApiResponse(code = 500,message = Result.SYS_GOOD_DETAIL_ERROR)
    })
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Result<ProductSysPageVo> detail(@RequestParam(value = "id",required = true)Long id){
        Result<ProductSysPageVo> result=new Result<ProductSysPageVo>();
        ProductSysPageVo productSysPageVo=productSysService.detail(id);
        result.setResult(productSysPageVo);
        return result;
    }
}
