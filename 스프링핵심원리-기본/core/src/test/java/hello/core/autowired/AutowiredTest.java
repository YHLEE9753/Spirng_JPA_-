package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import javax.swing.text.html.Option;
import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{

//        예외가 터진다. 이유는 Member noBean1 이 빈으로 등록이 되어있지 않기 때문문
//       @Autowired(required = true)
//        public void setNoBean1(Member noBean1){
//            System.out.println("noBean1 = " + noBean1);
//        }

        @Autowired(required = false) //의존관계가 없기 때문에 이 메서드 자체가 호출이 되지 않는다.
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired // 호출은 되지만, 대신에 null 로 들어온다
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired // 스프링빈이 없으면 Optional.empty 로 출력 있으면 Optional 에 값이 감싸저ㅕ 있는다.
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
