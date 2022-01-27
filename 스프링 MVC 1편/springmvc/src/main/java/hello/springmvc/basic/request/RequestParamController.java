package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody // return 값을 응답 바디에 넣어서 출력(@Restcontroller 와 동일)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody // return 값을 응답 바디에 넣어서 출력(@Restcontroller 와 동일)
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // return 값을 응답 바디에 넣어서 출력(@Restcontroller 와 동일)
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age){
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // return 값을 응답 바디에 넣어서 출력(@Restcontroller 와 동일)
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // requied true 이면 파라미터가 꼭 들어가야돼!!(에러발생)
            @RequestParam(required = false) Integer age){ // false 면 안들어가도 문제가 없다 (default : true)

        // int 안쓰는 이유 : int=null 이 불가능하여 false 하여 값이 안들어갈수가 없어 500번 에러 발생
        // 따라서 이런경우 Integer을 쓴다
       log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // return 값을 응답 바디에 넣어서 출력(@Restcontroller 와 동일)
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age){

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // return 값을 응답 바디에 넣어서 출력(@Restcontroller 와 동일)
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v0")
    public String modelAttributeV0(@RequestParam String username, @RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData); //lombok toString 기능

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
}
