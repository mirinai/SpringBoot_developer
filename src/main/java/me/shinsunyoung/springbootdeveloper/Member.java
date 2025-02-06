package me.shinsunyoung.springbootdeveloper;

import jakarta.persistence.*;               // JPA 관련 어노테이션 임포트
import lombok.AccessLevel;                 // Lombok의 접근 제어 레벨 임포트
import lombok.AllArgsConstructor;          // Lombok: 모든 필드를 포함한 생성자 자동 생성
import lombok.Getter;                      // Lombok: 모든 필드에 대한 getter 자동 생성
import lombok.NoArgsConstructor;           // Lombok: 기본 생성자 자동 생성

// ✅ Lombok 어노테이션
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 기본 생성자 자동 생성 (JPA를 위한 것)
// 외부에서 객체 생성을 제한하기 위해 PROTECTED 접근 제어자 사용

@AllArgsConstructor
// 모든 필드를 초기화하는 생성자 자동 생성

@Getter
// 모든 필드에 대한 getter 메서드 자동 생성

// ✅ JPA 엔티티 어노테이션
@Entity
// 해당 클래스가 JPA 엔티티임을 선언 (DB 테이블과 매핑됨)

public class Member {

    @Id
    // 해당 필드가 테이블의 Primary Key임을 선언

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본 키 자동 생성 전략 설정 (MySQL의 AUTO_INCREMENT와 유사)
    // IDENTITY 전략: 데이터베이스가 기본 키를 자동 증가시킴

    @Column(name = "id", updatable = false)
    // 테이블 컬럼과 매핑, 컬럼명은 'id'로 설정
    // updatable = false → 해당 필드는 수정 불가능 (PK는 일반적으로 수정되지 않음)

    private Long id;
    // 회원의 고유 ID (Primary Key)

    @Column(name = "name", nullable = false)
    // 테이블 컬럼과 매핑, 컬럼명은 'name'으로 설정
    // nullable = false → 값이 반드시 존재해야 함 (NOT NULL 제약 조건)

    private String name;
    // 회원의 이름

    public void changeName(String name){
        // 회원의 이름을 변경하는 메서드
        this.name = name;
    }
}

