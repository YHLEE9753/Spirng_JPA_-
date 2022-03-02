package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    // 옛 서블릿 방식 이용시
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") String memberAge
    ){
        log.info("username and age = {}", memberAge+memberName);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam String age
    ){
        log.info("username and age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false, name = "age",defaultValue = "guest") String userage
    ){
        log.info("test {}", userage+username);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> param){
        log.info("{},{}", param.get("username"),param.get("age"));
        return "Ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v0")
    public String modelAttributeV0(
            @RequestParam String username,
            @RequestParam int age
    ){
        HelloData helloData = new HelloData();
        helloData.setAge(age);
        helloData.setUsername(username);

        log.info("{},{}", helloData.getUsername(), helloData.getAge());
        log.info("{}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("{},{}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

}
