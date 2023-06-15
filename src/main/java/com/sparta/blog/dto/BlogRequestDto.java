package com.sparta.blog.dto;

import lombok.Getter;

@Getter
public class BlogRequestDto {
    private String title;
    private String name;
    private String content;
    private String password;
}