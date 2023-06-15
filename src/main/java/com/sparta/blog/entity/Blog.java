package com.sparta.blog.entity;

import com.sparta.blog.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "blog") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Blog extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "name", nullable = false, length = 500)
    private String name;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "password", nullable = false)
    private String password;

    public Blog(BlogRequestDto requestDto) {
        this.name = requestDto.getName();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }
}