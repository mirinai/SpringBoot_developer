package me.shinsunyoung.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest  // ✅ Spring Boot 애플리케이션 컨텍스트를 로드하여 통합 테스트 수행
@AutoConfigureMockMvc // ✅ MockMvc를 자동으로 설정하여 HTTP 요청/응답 테스트 가능
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc; // ✅ HTTP 요청/응답을 시뮬레이션하는 MockMvc 객체 (컨트롤러 테스트용)

    @Autowired
    private WebApplicationContext context; // ✅ Spring WebApplicationContext를 주입받아 MockMvc 설정에 사용

    @Autowired
    private MemberRepository memberRepository; // ✅ 테스트 데이터 저장/삭제를 위한 MemberRepository 주입

    @BeforeEach // ✅ 각 테스트 실행 전에 실행되는 메서드 (테스트 환경 초기화)
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        // MockMvc 객체를 WebApplicationContext로 초기화하여 컨트롤러 테스트 준비
    }

    @AfterEach // ✅ 각 테스트 실행 후에 실행되는 메서드 (테스트 데이터 정리)
    public void cleanUp() {
        memberRepository.deleteAll(); // 테스트 데이터 삭제 (DB 초기화)
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.") // ✅ 테스트 이름을 명확하게 표시 (테스트 리포트에 표시됨)
    @Test // ✅ JUnit 테스트 메서드로 지정
    public void getAllMembers() throws Exception {

        // ✅ Given (테스트 데이터 준비)
        final String url = "/test"; // 테스트할 API 엔드포인트
        Member savedMember = memberRepository.save(new Member(1L, "john"));
        // Member 객체를 데이터베이스에 저장

        // ✅ When (API 호출)
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));
        // GET 요청 수행, 응답은 JSON 형식으로 기대

        // ✅ Then (응답 검증)
        result
                .andExpect(status().isOk()) // 응답 HTTP 상태 코드가 200(OK)인지 검증
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                // JSON 응답의 첫 번째 객체(id)가 savedMember의 ID와 같은지 검증
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
        // JSON 응답의 첫 번째 객체(name)가 savedMember의 이름과 같은지 검증
    }
}
