package me.shinsunyoung.springbootdeveloper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// RESTful API 요청을 처리하는 컨트롤러 클래스
@RestController
public class QuizController {

    // GET 요청을 처리하는 메서드
    @GetMapping("/quiz")
    public ResponseEntity<String> quiz(@RequestParam("code") int code){ // 요청 파라미터 'code'를 받음
        switch (code){
            case 1:
                // code가 1인 경우 201 Created 상태 반환
                return ResponseEntity.created(null).body("Created!");
            case 2:
                // code가 2인 경우 400 Bad Request 상태 반환
                return ResponseEntity.badRequest().body("Bad Request!");
            default:
                // 그 외의 경우 200 OK 상태 반환
                return ResponseEntity.ok().body("OK!");
        }
    }

    // POST 요청을 처리하는 메서드
    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestBody Code code){ // 요청 본문에서 Code 객체를 받음
        switch (code.value()){ // Code 객체의 value 값을 확인
            case 1:
                // value가 1인 경우 403 Forbidden 상태 반환
                return ResponseEntity.status(403).body("Forbidden!");
            default:
                // 그 외의 경우 200 OK 상태 반환
                return ResponseEntity.ok().body("OK!");
        }
    }

    // 요청 본문으로 받을 Code 객체를 정의하는 record 클래스
    record Code(int value){
        // int 타입의 value 필드를 가짐
    }
}
