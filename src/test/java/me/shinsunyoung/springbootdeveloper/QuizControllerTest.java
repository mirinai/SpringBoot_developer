package me.shinsunyoung.springbootdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static me.shinsunyoung.springbootdeveloper.QuizController.Code;

// Spring Boot 테스트 환경을 구성하는 클래스
@SpringBootTest // 실제 애플리케이션 컨텍스트를 로드하여 테스트 수행
@AutoConfigureMockMvc // MockMvc를 자동으로 설정해주는 애너테이션
class QuizControllerTest {

    @Autowired
    protected MockMvc mockMvc; // HTTP 요청 및 응답을 모의하기 위한 객체

    @Autowired
    private WebApplicationContext context; // 웹 애플리케이션 컨텍스트로 테스트 환경 구성에 사용

    @Autowired
    private ObjectMapper objectMapper; // JSON 직렬화 및 역직렬화를 위한 객체

    // 각 테스트 실행 전에 호출되는 메서드로 MockMvc를 초기화함
    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context) // WebApplicationContext를 기반으로 MockMvc 설정
                .build(); // MockMvc 객체 생성
    }

    // GET 요청 테스트: /quiz?code=1 요청 시 201 Created와 "Created!" 반환
    @DisplayName("quiz(): GET /quiz?code=1이면 응답코드는 201, 응답 본문은 Created!를 리턴한다.")
    @Test
    public void getQuiz1() throws Exception{

        // given
        final String url = "/quiz"; // 요청 URL

        // when
        final ResultActions result = mockMvc.perform(get(url).param("code", "1")); // GET 요청 수행

        // then
        result
                .andExpect(status().isCreated()) // 응답 코드 201 검증
                .andExpect(content().string("Created!")); // 응답 본문 검증
    }

    // GET 요청 테스트: /quiz?code=2 요청 시 400 Bad Request와 "Bad Request!" 반환
    @DisplayName("quiz(): GET /quiz?code=2면 응답코드는 400, 응답 본문은 Bad Request!를 리턴한다.")
    @Test
    public void getQuiz2() throws Exception{

        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(get(url).param("code","2"));

        // then
        result
                .andExpect(status().isBadRequest()) // 응답 코드 400 검증
                .andExpect(content().string("Bad Request!")); // 응답 본문 검증
    }

    // POST 요청 테스트: {"value":1} 전송 시 403 Forbidden과 "Forbidden!" 반환
    @DisplayName("quiz(): POST /quiz에 요청 본문이 {\"value\":1}이면 응답코드는 403, 응답본문은 Forbidden!을 리턴한다.")
    @Test
    public void postQuiz1() throws Exception{

        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON) // JSON 형식으로 Content-Type 설정
                .content(objectMapper.writeValueAsString(new Code(1))) // JSON 데이터 전송
        );

        // then
        result
                .andExpect(status().isForbidden()) // 응답 코드 403 검증
                .andExpect(content().string("Forbidden!")); // 응답 본문 검증
    }

    // POST 요청 테스트: {"value":13} 전송 시 200 OK와 "OK!" 반환
    @DisplayName("quiz(): POST /quiz에 요청 본문이 {\"value\":13}이면 응답코드는 200, 응답본문은 OK!을 리턴한다.")
    @Test
    public void postQuiz13() throws Exception{

        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new QuizController.Code(13))) // JSON 데이터 전송
        );

        // then
        result
                .andExpect(status().isOk()) // 응답 코드 200 검증
                .andExpect(content().string("OK!")); // 응답 본문 검증
    }

}
