package org.forbes.comm.enums;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.collect.Maps;

/***
 * ProductStausEnum概要说明：商品状态
 * @author lzw
 */
public enum ProductStausEnum {

	FREEZE("1","未上架"),
    NORMAL("2","上架"),
    TOBEAUDITED("3","待审核"),
    AUDITFAILURE("4","审核失败");

    /***编码
     */
    private String code;

    /***名称
     */
    private String name;


    /***
     * existsProductStausEnum方法慨述:
     * @param code
     * @return boolean
     * @创建人 lzw
     * @创建时间 2019年12月12日
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static boolean existsProductStausEnum(String code){
        return Arrays.asList(ProductStausEnum.values()).stream()
                .filter(productStaus -> ((ProductStausEnum)productStaus).getCode().equals(code)).count() > 0 ;
    }


    /***
     * receProductStaus方法慨述:
     * @return List<Map<String,String>>
     * @创建人 lzw
     * @创建时间 2019年12月12日
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static List<Map<String,String>> receProductStaus(){
        return Arrays.asList(ProductStausEnum.values()).stream().map(productStaus -> {
            Map<String,String> reponseMap = Maps.newHashMap();
            ProductStausEnum producttStaus = ((ProductStausEnum)productStaus);
            reponseMap.put("code", producttStaus.getCode());
            reponseMap.put("name", producttStaus.getName());
            return reponseMap;
        }).collect(Collectors.toList());
    }

    /***
     *
     * 构造函数:
     * @param code
     * @param name
     */
    ProductStausEnum(String code, String name){
        this.code = code;
        this.name = name;
    }




    /**
     * @return code
     */
    public String getCode() {
        return code;
    }


    /**
     * @param code 要设置的 code
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 要设置的 name
     */
    public void setName(String name) {
        this.name = name;
    }

}
