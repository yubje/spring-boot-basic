package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
Service에서 컴포넌트 스캔을 사용하지 않 설정 직접 하지 않고 (Autowired, Service)
자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration
public class SpringConfig {
    /*
    private DataSource dataSource;

    // jpa 도입을 위한 설정
    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

     */

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /* Data Jpa에서는 필요 없음
    @Bean
    public MemberRepository memberRepository() {
//        return new JdbcTemplateMemberRepository(dataSource);
        // return new JdbcMemberRepository(dataSource);
        // return new MemoryMemberRepository();
       return new JpaMemberRepository(em);
    }

     */


}
