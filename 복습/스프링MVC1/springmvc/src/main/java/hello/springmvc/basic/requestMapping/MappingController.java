package hello.springmvc.basic.requestMapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping(value = {"/hello-basic", "/hello-go"})
    public String helloBasic(){
        log.info("test");
        return "ok";
    }

    @GetMapping("/mapping-get-v1")
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingGetV2(@PathVariable("userId") String data){
        log.info("mappingGetV2={}", data);
        return "ok";
    }

    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingGetV3(@PathVariable String userId, @PathVariable String orderId){
        log.info("userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }


}
