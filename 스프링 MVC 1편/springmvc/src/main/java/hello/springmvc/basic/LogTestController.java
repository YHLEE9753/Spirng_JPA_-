package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController //리턴하는 값을 메시지바디에 출력 <-> @Controller 는 view 반환
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass()); //내클래스지정 - annotation 대체가능

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

//        System.out.println("name = " + name);
        log.trace("trace log = {}",name);
        log.debug("debug log = {}",name);
        log.info("info log = {}",name);
        log.warn("warn log = {}",name);
        log.error("error log = {}",name);

        return "Ok";
    }
}
