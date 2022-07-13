package com.ll.exam;

import java.util.Scanner;

public class WiseSayingController {

    private Scanner sc;
    private WiseSayingRepository wiseSayingRepository;

    //생성자
    WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingRepository = new WiseSayingRepository();

    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------");
        for (int i = wiseSayingRepository.wiseSayings.size() - 1; i >= 0; i--) { // last_index = size - 1
            WiseSaying wiseSaying_ = wiseSayingRepository.wiseSayings.get(i); // wiseSaying_ 객체에 넣어서 출력
            System.out.printf("%d / %s / %s \n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }

        public void write(Rq rq) {

            // 1.content, author, wiseSayingLastId 처리
            System.out.printf("명언 : ");
            String content = sc.nextLine().trim(); // trim()으로 시작과 끝 공백 제거, 중간 공백은 제거 불가
            System.out.printf("작가 : ");
            String author = sc.nextLine().trim();
            int id = ++wiseSayingRepository.wiseSayingLastId; // 명언 글 번호 증가

            //2.wiseSaying 객체 생성 및 add
            WiseSaying wiseSaying = new WiseSaying(id, content, author);
            wiseSayingRepository.wiseSayings.add(wiseSaying);

            //3.해당 id 등록 완료 멘트
            System.out.printf("%d번 명언이 등록되었습니다.\n", id);


        }
        public void modify(Rq rq) {
            //1-1.url에 입력된 id 얻기
            int paramId = rq.getIntParam("id", 0);

            //1-2.url에 입력된 id가 없다면 작업중지
            if (paramId ==0) {
                System.out.println("id를 입력해주세요");
                return;
            }

            //2-1.url에 입력된 id에 해당하는 명언객체 찾기
            WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

            //2-2.찾지 못했다면 중지
            if (foundWiseSaying == null) {
                System.out.printf("%d번 명언은 존재하지 않습니다. \n", paramId);
                return;
            }

            //3-1.기존 명언 content와 새로운 명언 content 출력
            System.out.printf("명언(기존) : %s\n", foundWiseSaying.content);
            System.out.printf("명언 : ");
            foundWiseSaying.content = sc.nextLine();

            //3-2.기존 명언 author와 새로운 명언 author 출력
            System.out.printf("작가(기존) :%s\n",foundWiseSaying.author );
            System.out.printf("작가 : ");
            foundWiseSaying.author = sc.nextLine();

            //4.해당id 명언 수정 완료 멘트
            System.out.printf("%d번 명언이 수정되었습니다.\n", paramId);


        }
        public void remove(Rq rq) {
            //1-1.URL에 입력된 id 얻기
            int paramId = rq.getIntParam("id",0);

            //1-2. URL에 입력된 id가 없다면 작업 중지
            if(paramId == 0) {
                System.out.println("id를 입력해주세요");
                return;
            }

            //2-1.URL에 입력된 id에 해당하는 명언 객체 찾기
            WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

            //2-2.찾지 못했다면 중지
            if(foundWiseSaying == null) {
                System.out.printf("%d번 명언은 존재하지 않습니다.\n",paramId);
                return;
            }

            //3.입력된 id에 해당하는 명언 객체를 리스트에서 삭제
            wiseSayingRepository.wiseSayings.remove(foundWiseSaying);

            System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
        }




}
