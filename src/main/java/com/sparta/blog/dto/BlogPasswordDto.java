package com.sparta.blog.dto;

import com.sparta.blog.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogPasswordDto {
    private Long id;
    private String title;
    private String name;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


    public BlogPasswordDto(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.name = blog.getName();
        this.content = blog.getContent();
        this.createAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
}