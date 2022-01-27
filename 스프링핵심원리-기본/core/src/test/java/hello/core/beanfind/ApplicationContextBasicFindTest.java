package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        Object memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());

    }

    @Test
    @DisplayName("빈 이름 없이 타입으로 조회")
    void findBeanByType(){
        Object memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    // 스프링빈에 등록된 인터페이스 타입을 읽으므로(반환타입을 설정했으므로) 인터페이스가 아니라 구체적인거 적어도 되
    // 하지만 구체적인거는 좋지않아. 항상 역할과 구현을 구별해야되. 역할에 의존. 아래 코드는 구현에 의존
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        Object memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    // 실패케이스도 항상
    @Test
    @DisplayName("빈 ㅇ름으로 조회X")
    void findBeanByNameX(){
//        ac.getBean("xxxx", MemberService.class);
//        MemberService xxxx = ac.getBean("xxxx", MemberService.class);
//        아래 코드는 Assertions(junit).assertThrows
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));

    }
}
