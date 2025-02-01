import org.assertj.core.api.Assertions; // AssertJ의 Assertions 클래스 임포트
import org.junit.jupiter.api.Test;      // JUnit 5의 Test 어노테이션 임포트

public class JUnitQuiz {

    @Test
    public void junitTest1() {
        // 문자열 변수 선언
        String name1 = "john";
        String name2 = "john";
        String name3 = "joan";

        // ✅ Null 검증 (isNotNull())
        Assertions.assertThat(name1).isNotNull(); // name1이 null이 아님을 검증
        Assertions.assertThat(name2).isNotNull(); // name2가 null이 아님을 검증
        Assertions.assertThat(name3).isNotNull(); // name3이 null이 아님을 검증

        // ✅ 값이 같은지 검증 (isEqualTo())
        Assertions.assertThat(name1).isEqualTo(name2); // name1과 name2가 같은 값인지 검증 (둘 다 "john")

        // ✅ 값이 다른지 검증 (isNotEqualTo())
        Assertions.assertThat(name1).isNotEqualTo(name3); // name1과 name3이 다른 값인지 검증 ("john" ≠ "joan")
    }

    @Test
    public void junitTest2(){
        // ✅ 정수 변수 선언
        int num1 = 15;   // 양수
        int num2 = 0;    // 0
        int num3 = -5;   // 음수

        // ✅ 양수인지 검증 (isPositive())
        Assertions.assertThat(num1).isPositive(); // num1이 양수인지 확인 (15는 양수이므로 테스트 통과)

        // ✅ 0인지 검증 (isZero())
        Assertions.assertThat(num2).isZero();     // num2가 0인지 확인 (0이므로 테스트 통과)

        // ✅ 음수인지 검증 (isNegative())
        Assertions.assertThat(num3).isNegative(); // num3이 음수인지 확인 (-5는 음수이므로 테스트 통과)

        // ✅ 특정 값보다 큰지 검증 (isGreaterThan())
        Assertions.assertThat(num1).isGreaterThan(num2); // num1(15)이 num2(0)보다 큰지 확인 (테스트 통과)

        // ✅ 특정 값보다 작은지 검증 (isLessThan())
        Assertions.assertThat(num3).isLessThan(num2);    // num3(-5)이 num2(0)보다 작은지 확인 (테스트 통과)
    }
}
