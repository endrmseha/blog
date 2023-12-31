package com.sparta.blog.service;

import com.sparta.blog.dto.BlogPasswordDto;
import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        // RequestDto -> Entity
        Blog blog = new Blog(requestDto);

        // DB 저장
        Blog saveBlog = blogRepository.save(blog);

        // Entity -> ResponseDto
        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);

        return blogResponseDto;
    }

    public List<BlogPasswordDto> getBlogs() {
        // DB 조회
        return blogRepository.findAllByOrderByModifiedAtDesc().stream().map(BlogPasswordDto::new).toList();
    }

    public List<BlogPasswordDto> getBlogsByKeyword(String keyword) {
        return blogRepository.findAllByTitleContainsOrderByModifiedAtDesc(keyword).stream().map(BlogPasswordDto::new).toList();
    }

    @Transactional
    public Long updateBlog(Long id, BlogRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog blog = findBlog(id);

        // blog 내용 수정
        blog.update(requestDto);

        return id;
    }

    public Long deleteBlog(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog blog = findBlog(id);

        // blog 삭제
        blogRepository.delete(blog);
        return id;
    }

    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}