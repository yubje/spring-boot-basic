package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // DI - 생성자 주입 (권장)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* DI - 필드 주입 (비추)
    * @Autowired private MemberService memberService;
    * */

    /* DI - setter 주입 - public 하게 노출이 되는 문제가 있음, 호출되지 않아야 하는 메소드인데 아무데서나 호출 가능
    * @Autowired
    * public void setMemberService(MemberService memberService) {
    *   this.memberService = memberService;
    * }
    * */
}
