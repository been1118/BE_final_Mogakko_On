package com.mogakko.be_final.domain.chatMessage.util;

public interface BadWords {
    //BadWord
    String[] badWords = { "슈발", "미친", "뻐큐", "썅", "섹스", "ㅅㅂ", "ㅆㅂ",
            "시발", "씨발", "ㅗ", "fuck", "개새끼", "개색기", "씹", "ㅁㅊ", "ㅈㄹ",
            "ㄷㅊ", "꺼져", "닥쳐", "지랄", "개새기" , "좆", "존나", "놈", "년", "련"
            , "개새", "스벌", "씨벌", "쓰벌", "병신", "ㅂㅅ", "븅신", "ㅅ1발"};
    //BadWord 글자 사이에 들어가면 같이 필터링 될 문자들
    String[] sings = { " ", "1", ",", ".", "!", "?", "2", "3", "@", "4", "5",
            "6", "7", "8", "9", "0", "ㅡ", "_", "~"};
    //바뀌게 될 문자
    String substituteValue = "*";
}