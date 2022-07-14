package com.ll.exam;

import java.util.Map;
import java.util.Objects;

public class WiseSaying {
    int id;
    String content;
    String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public WiseSaying(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.content = (String) map.get("content");
        this.author = (String) map.get("author");
    }


    //모든 객체를 문자열로 변환
    @Override
    public String toString() {
        return "WiseSaying{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    //Json 형태로 변환
   public String toJson() {
        //문자열은 "" 붙이는 것이 Json의 규격
        return """
                {
                    "id": %d,
                    "content": "%s",
                    "author": "%s"
                }
                """
                .stripIndent()
                .formatted(id, content, author)
                .trim();

    }
    //equals()와 hashCode()는 FileDBTest에서만 사용 -> TDD 진행 안할 시 필요 X
    /*
    직접 구현 ver.equals()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // 객체여부 확인
        if (o instanceof WiseSaying == false) return false; // WiseSaying 타입 여부 확인

        WiseSaying other = (WiseSaying) o;

        if (this.id != other.id) return false;
        if (this.content.equals(other.content) == false) return false;
        if (this.author.equals(other.author) == false) return false;

        return true;
    }
    */

    //인텔리제이 제공 ver.equals()와 hashCode()
    //generate - equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WiseSaying that = (WiseSaying) o;

        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return author != null ? author.equals(that.author) : that.author == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}