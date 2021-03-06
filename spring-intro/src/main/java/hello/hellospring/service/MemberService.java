package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
@Transactional // jpa 사용 시 필요
public class MemberService {

    private final MemberRepository memberRepository;

    // Dependency Injection (DI) - 외부에서 memberRepository를 넣어줌 (생성자 주입)
    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();



    }

    /**
     * 같은 이름이 있는 중복 회원 가입 방지
     */
    private void validateDuplicateMember(Member member) {

         /*
        Optional<Member> result = memberRepository.findByName(member.getName());


        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {              // Optional을 사용해서 쓸 수 있는 기능
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // 결과가 존재하면 이미 존재하는 회원이므로 가입 불가
                });
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
