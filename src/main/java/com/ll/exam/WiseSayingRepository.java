package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    public List<WiseSaying> wiseSayings;
    public int wiseSayingLastId;

    //생성자
    WiseSayingRepository() {
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
    }

    public WiseSaying findById(int paramId) { //Controller에서 접근해서 사용해야하므로 public으로
        // 향상된 for문(자료형 변수명 : 배열명) -> 여기에 사용된 변수는 for문 안에서만 사용되는 지역변수
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }

        return null;
    }
}
