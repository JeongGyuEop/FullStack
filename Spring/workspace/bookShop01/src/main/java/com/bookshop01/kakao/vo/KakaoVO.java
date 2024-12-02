package com.bookshop01.kakao.vo;

public class KakaoVO {
    private String id;
    private String email;
    private String nickname;

    public KakaoVO(String id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    // Getter 및 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
