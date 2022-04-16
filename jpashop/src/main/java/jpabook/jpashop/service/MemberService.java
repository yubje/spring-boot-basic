package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // readOnly true 로 설정
@RequiredArgsConstructor // 생성자 주입 생략
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    // Constructor Injection (생성자 주입)
    // @Autowired 생성자가 하나만 있는 경우 자동으로 injection이 되어 생략 가능
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */


    /**
     *  회원 가입
     */
    @Transactional   // 옵션 미추가 시 readOnly는 false로 적용됨
    public Long join(Member member) {
        // 중복 회원 검
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    /**
     * 중복 회원 검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    /**
     *  회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     * @param memberId
     * @return Member
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
