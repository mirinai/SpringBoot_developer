import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    /**
     * 모든 테스트가 실행되기 전에 한 번만 호출되는 메서드
     * 테스트 클래스 로딩 시 가장 먼저 실행됨
     * static으로 선언해야 하는 이유:
     * - JUnit이 테스트 인스턴스를 생성하기 전에 호출되므로 인스턴스 없이 실행할 수 있어야 함
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    /**
     * 각 테스트 메서드가 실행되기 전에 매번 호출되는 메서드
     * 테스트 메서드마다 독립적으로 실행되므로 인스턴스 메서드로 선언 가능
     */
    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    /**
     * 실제 테스트 코드가 작성되는 메서드
     * JUnit은 @Test 애너테이션이 붙은 메서드를 테스트로 인식함
     */
    @Test
    public void test2() {
        System.out.println("test2");
    }

    /**
     * 또 다른 테스트 메서드
     * 각 테스트는 독립적으로 실행되며, before/after 메서드의 영향을 받음
     */
    @Test
    public void test3() {
        System.out.println("test3");
    }

    /**
     * 모든 테스트가 끝난 후 한 번만 호출되는 메서드
     * 테스트 인스턴스가 사라지기 전에 호출되며 리소스 정리에 사용됨
     * static으로 선언해야 하는 이유:
     * - JUnit이 테스트 인스턴스 해제 후에도 실행할 수 있도록 하기 위함
     */
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    /**
     * 각 테스트 메서드가 끝난 후 매번 호출되는 메서드
     * 테스트마다 독립적으로 실행되므로 인스턴스 메서드로 선언 가능
     */
    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}
