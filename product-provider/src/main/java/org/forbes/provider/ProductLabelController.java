package org.forbes.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.IProductLabelService;
import org.forbes.comm.constant.*;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.ProductLabelPageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzw
 * @date 2020/1/8 10:49
 */

@RestController
@RequestMapping("/label")
@Api(tags={"商品标签管理"})
@Slf4j
public class ProductLabelController {

    @Autowired
    IProductLabelService productLabelService;

    /***
     * page方法概述:分页查询商品标签
     * @param basePageDto, productLabelPageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.forbes.dal.entity.ProductLabel>>
     * @创建人 Tom
     * @创建时间 2020/1/8 11:10
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ApiOperation("分页查询商品标签")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=ProductLabelPageDto.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PAGE_LABEL_MSG_ERROR),
            @ApiResponse(code=200,response=Result.class, message = Result.PAGE_LABEL_MSG)
    })
    public Result<IPage<ProductLabel>> page(BasePageDto basePageDto, ProductLabelPageDto productLabelPageDto){
        Result<IPage<ProductLabel>> result = new Result<>();
        QueryWrapper<ProductLabel> qw = new QueryWrapper<ProductLabel>();
        if(ConvertUtils.isNotEmpty(productLabelPageDto)){
            if(ConvertUtils.isNotEmpty(productLabelPageDto.getLabelName())){
                qw.like(PermsCommonConstant.LABEL_NAME,productLabelPageDto.getLabelName());
            }
        }
        IPage<ProductLabel> page = new Page<ProductLabel>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ProductLabel> s = productLabelService.page(page,qw);
        result.setResult(s);
        return result;
    }

    /***
     * getById方法概述:
     * @param id
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductLabel>
     * @创建人 Tom
     * @创建时间 2020/1/9 9:35
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/get-by-id", method = RequestMethod.GET)
    @ApiOperation("通过商品标签id查看商品标签信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "商品标签id")
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PAGE_LABEL_MSG_ERROR),
            @ApiResponse(code=200,message = Result.PAGE_LABEL_MSG)
    })
    public Result<ProductLabel> getById(@RequestParam(value = "id",required = true)Long id){
        Result<ProductLabel> result=new Result<ProductLabel>();
        //查询商品标签信息
        ProductLabel productLabel = productLabelService.getById(id);
        result.setResult(productLabel);
        return result;
    }

    /***
     * addProductLabel方法概述:
     * @param productLabel
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductLabel>
     * @创建人 Tom
     * @创建时间 2020/1/8 11:24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponse(code=200,message = Result.ADD_LABEL_MSG)
    @ApiOperation("添加商品标签")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_LABEL_MSG_ERROR),
    })
    public Result<ProductLabel> addProductLabel(@RequestBody @Validated(value=SaveValid.class) ProductLabel productLabel){
        Result<ProductLabel> result=new Result<ProductLabel>();
        String name = productLabel.getLabelName();
        //查询是否和其他角色标签一致
        int existsCount = productLabelService.count(new QueryWrapper<ProductLabel>().eq(DataColumnConstant.LABEL_NAME, name));
        if(existsCount > 0) {
            result.setBizCode(BizResultEnum.LABEL_NAME_EXISTS.getBizCode());
            result.success(String.format(BizResultEnum.LABEL_NAME_EXISTS.getBizFormateMessage(), name));
            return result;
        }
        productLabelService.save(productLabel);
        result.setResult(productLabel);
        return result;
    }

    /***
     * updateProductLabel方法概述:编辑商品标签
     * @param productLabel
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductLabel>
     * @创建人 Tom
     * @创建时间 2020/1/8 13:17
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("编辑商品标签")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public  Result<ProductLabel> updateProductLabel(@RequestBody @Validated(value=UpdateValid.class) ProductLabel productLabel){
        Result<ProductLabel> result=new Result<ProductLabel>();
        ProductLabel old = productLabelService.getById(productLabel.getId());
        if(ConvertUtils.isEmpty(old)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //判断当前编辑商品标签与输入的是否一致
        if (!old.getLabelName().equals(productLabel.getLabelName())) {
            String name = productLabel.getLabelName();
            //查询是否和其他编辑商品标签一致
            int existsCount = productLabelService.count(new QueryWrapper<ProductLabel>().eq(DataColumnConstant.LABEL_NAME,name));
            if(existsCount > 0) {
                result.setBizCode(BizResultEnum.LABEL_NAME_EXISTS.getBizCode());
                result.success(String.format(BizResultEnum.LABEL_NAME_EXISTS.getBizFormateMessage(), name));
                return result;
            }
        }
        productLabelService.updateById(productLabel);
        result.setResult(productLabel);
        return result;
    }

    /***
     * delete方法概述:删除商品标签
     * @param id
     * @return org.forbes.comm.vo.Result<org.forbes.dal.entity.ProductLabel>
     * @创建人 Tom
     * @创建时间 2020/1/8 13:19
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("删除商品标签")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id",value = "商品标签ID",required = true)
    })
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<ProductLabel> delete(@RequestParam(name="id",required=true) String id) {
        Result<ProductLabel> result = new Result<ProductLabel>();
        ProductLabel productLabel = productLabelService.getById(id);
        if(ConvertUtils.isEmpty(productLabel)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        productLabelService.removeById(id);
        return result;
    }

    /***
     * deleteBatch方法概述:批量删除商品标签
     * @param ids
     * @return org.forbes.comm.vo.Result<java.lang.Boolean>
     * @创建人 Tom
     * @创建时间 2020/1/8 13:20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("批量删除商品标签")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids",value = "商品标签IDs",required = true)
    })
    @RequestMapping(value = "/delete-batch", method = RequestMethod.DELETE)
    public Result<Boolean> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        // 定义实体类的数据库查询对象
        Result<Boolean> result = new Result<Boolean>();
        try {
            productLabelService.removeByIds(Arrays.asList(ids.split(CommonConstant.SEPARATOR)));
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }

}
