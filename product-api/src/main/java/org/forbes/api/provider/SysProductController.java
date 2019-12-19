package main.java.org.forbes.api.provider;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/18 11:47
 * @Version 1.0
 **/
@RestController(value="apiProductController")
@RequestMapping("/product-api")
@Api(tags={"商家外部接口调用"})
@Slf4j
public class SysProductController {
}
