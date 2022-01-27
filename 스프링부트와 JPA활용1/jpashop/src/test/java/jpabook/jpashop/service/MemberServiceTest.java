package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //이게 있어야 롤백이 된다!!(Test 에서만 작용되는것! Service 이런데서는 롤백안돼!!), + jpa 쓸거면 무조건 넣자!
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false) // Transactional 은 기본적으로 rollback 을 해버리므로 insert 쿼리를 보고 싶으면 @Rollback(false) 로 설정하자
    public void 회원가입() throws Exception{
        //given(주어졌을때)
        Member member = new Member();
        member.setName("kim");

        //when(하면)
        Long savedId = memberService.join(member);

        //then(결과가 이렇게 나온다)
        em.flush(); // 혹은 @Rollback(false) 대신 flush 를 해주어도 된다.
        assertEquals(member, memberRepository.findOne(savedId)); // 같은 transaction 안에서 pk 값이 같으면 동일성 보장해준다!!

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
        memberService.join(member2); //@Test(expected = IllegalStateException.class) 를 통해 코드 간다하게 가능
//        try {
//            memberService.join(member2); // 예외가 발생해야 한다.
//        } catch(IllegalStateException e){
//            return;
//        }
        //then
        fail("예외가 발생해야 한다.");

    }
}