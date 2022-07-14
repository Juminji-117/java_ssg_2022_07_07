package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private List<WiseSaying> wiseSayings; // repo 클래스에서만 사용하므로 private
    private int wiseSayingLastId; // repo 클래스에서만 사용하므로 private

    //생성자
    WiseSayingRepository() {
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
    }

    public WiseSaying findById(int paramId) { //Controller에서 접근해서 사용해야하므로 public으로
        // 향상된 for문(자료형 변수명 : 배열명) -> 여기에 사용된 변수는 for문 안에서만 사용되는 지역변수
        //위에 생성된 배열 wiseSayings에서 paramId와 일치하는 id 리턴
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }

        return null;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings; // wiseSayings 배열 전체 리턴
    }

    public WiseSaying write(String content, String author) {
        //1. last id index 증가
        int id = ++wiseSayingLastId;
        //2. id, content, author 3가지 속성 가진 WiseSaying 타입의 wiseSaying 객체 생성
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        //3. wiseSayings 배열에 wiseSaying 객체 넣기
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }
    public void remove(int paramId) {
        // 1. 해당 id 가진 WiseSaying 타입의 wiseSaying(findById() 결과)을 foundWiseSaying에 넣기
        WiseSaying foundWiseSaying = findById(paramId);
        //2. 배열 wiseSayings에서 해당 foundWiseSaying 삭제
        wiseSayings.remove(foundWiseSaying);
    }

    public void modify(int paramId, String content, String author) {
        // 1.해당 id 가진 WiseSaying 타입의 wiseSaying(findById() 결과)을 foundWiseSaying에 넣기
        WiseSaying foundWiseSaying = findById(paramId);
        // 2. foundWiseSaying 각 속성(content, author) 수정
        foundWiseSaying.content = content;
        foundWiseSaying.author = author;

    }

}


