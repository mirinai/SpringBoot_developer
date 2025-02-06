package me.shinsunyoung.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// MemberRepository의 데이터베이스 동작을 테스트하는 클래스입니다.
@DataJpaTest
class MemberRepositoryTest {

    // MemberRepository를 주입받아 테스트에 사용합니다.
    @Autowired
    MemberRepository memberRepository;

    // SQL 스크립트를 통해 테스트 데이터를 삽입한 후, 모든 회원을 조회하는 테스트입니다.
    @Sql("/insert-members.sql")
    @Test
    void getAllMembers(){

        // when: 모든 회원을 조회합니다.
        List<Member> members = memberRepository.findAll();

        // then: 회원 수가 3명인지 검증합니다.
        assertThat(members.size()).isEqualTo(3);
    }

    // SQL 스크립트를 통해 테스트 데이터를 삽입한 후, ID로 회원을 조회하는 테스트입니다.
    @Sql("/insert-members.sql")
    @Test
    void getMemberById(){

        // when: ID가 2인 회원을 조회합니다.
        Member member = memberRepository.findById(2L).get();

        // then: 조회된 회원의 이름이 "B"인지 검증합니다.
        assertThat(member.getName()).isEqualTo("B");
    }

    // SQL 스크립트를 통해 테스트 데이터를 삽입한 후, 이름으로 회원을 조회하는 테스트입니다.
    @Sql("/insert-members.sql")
    @Test
    void getMemberByName(){

        // when: 이름이 "C"인 회원을 조회합니다.
        Member member = memberRepository.findByName("C").get();

        // then: 조회된 회원의 ID가 3인지 검증합니다.
        assertThat(member.getId()).isEqualTo(3);
    }

    // 새로운 회원을 저장한 후 정상적으로 저장되었는지 검증하는 테스트입니다.
    @Test
    void saveMember(){
        // given: 새로운 회원 객체를 생성합니다.
        Member member = new Member(1L, "A");

        // when: 회원을 저장합니다.
        memberRepository.save(member);

        // then: 저장된 회원의 이름이 "A"인지 검증합니다.
        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }

    // 여러 회원을 저장한 후 정상적으로 저장되었는지 검증하는 테스트입니다.
    @Test
    void saveMembers(){
        // given: 두 명의 회원 객체를 생성합니다.
        List<Member> members = List.of(new Member(2L, "B"), new Member(3L, "C"));

        // when: 회원 목록을 저장합니다.
        memberRepository.saveAll(members);

        // then: 저장된 회원 수가 2명인지 검증합니다.
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    // 특정 ID의 회원을 삭제하고 정상적으로 삭제되었는지 검증하는 테스트입니다.
    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById(){
        // when: ID가 2인 회원을 삭제합니다.
        memberRepository.deleteById(2L);

        // then: 삭제된 회원이 존재하지 않는지 검증합니다.
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    // 모든 회원을 삭제하고 데이터가 없는지 검증하는 테스트입니다.
    @Sql("/insert-members.sql")
    @Test
    void deleteAll(){
        // when: 모든 회원을 삭제합니다.
        memberRepository.deleteAll();

        // then: 저장된 회원 수가 0인지 검증합니다.
        assertThat(memberRepository.findAll().size()).isZero();
    }

    // 특정 회원의 이름을 변경하고 정상적으로 업데이트되었는지 검증하는 테스트입니다.
    @Sql("/insert-members.sql")
    @Test
    void update(){
        // given: ID가 2인 회원을 조회합니다.
        Member member = memberRepository.findById(2L).get();

        // when: 회원의 이름을 "BC"로 변경합니다.
        member.changeName("BC");

        // then: 변경된 이름이 정상적으로 반영되었는지 검증합니다.
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }

    // 각 테스트가 끝난 후 데이터베이스를 정리하기 위해 실행되는 메서드입니다.
    @AfterEach
    public void cleanUp(){
        memberRepository.deleteAll();
    }

}