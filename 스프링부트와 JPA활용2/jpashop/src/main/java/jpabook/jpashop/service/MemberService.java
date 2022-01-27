package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//JPA 사용시 @Transactional 을 사용해야 Lazy loading 등이 적용된다.
@Transactional(readOnly = true) // readOnly = true 사용시 조회에서 성능을 좀더 최적화한다. - 보통 조회가 많으니 이렇게 적용한다.
@RequiredArgsConstructor // 5. final 이 있는 필드만 가지고 생성자를 만들어준다. 이거 쓱 생성자 생략해도 가능하다.
public class MemberService {

//    @Autowired // 1. 수정이 불가
    private final MemberRepository memberRepository; // 4. 무조건 1개 만들어야 되므로 final 넣는게 선호된다. 그 후 lombok 적용시 코드가 단순해진다.

//    @Autowired // 2. 수정이 가능 - 근데 런타임에서 누군가 바꾸면 치명적인 버그(찾기가 어렵다) 왜냐면 실제로 바꿀일이 거의 없으니까
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

//    @Autowired // 3. 생성자 주입으로 최초 선언시 주입하자 - 생성자를 자주 사용하자! //1개만 사용시 @Autowired 안만들어도 된다.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     */
    @Transactional // readOnly default 는 false
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        // 근데 2가지가 동시에 접근시 2가지가 동시에 저장 가능하다. 따라서 최종적으로 한번더 validation 을 db 딴에서 만들어주어야한다.
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회 - @Transactional(readOnly = true) 적용
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 단건 조회 - @Transactional(readOnly = true) 적용
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name){
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
