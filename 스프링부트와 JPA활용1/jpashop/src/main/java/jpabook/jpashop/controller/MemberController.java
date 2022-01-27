package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Valid MemberForm form, BindingResult result){

        //BindingResult 를 통해 에러 내용을 담을 수 있고, 에러 존재시 이동하는 것 처리 가능능
        if (result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        // 사실 이부분도 entity 보다는 DTO 로 뿌리는게 맞다 (실무에서 더 복잡해지면)
        // entity 를 손안대고 심플하게 할 수 있는 부분이라 이대로 진행 + for 문 돌면서 필요한 데이터만 찍으니 괜찮 -> 템플릿에서는 서버단에서 진행되므로 괜찮다.
        // API 를 만들때는 이유를 불문하고 절대 entity 를 넘기면 안된다.
        // API 스펙이 변화가 생기고 + 보안상의 정보가 노출될 수 있다. -> 반드시 DTO
        return "members/memberList";
    }
}
