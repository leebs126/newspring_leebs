package com.springboot.ch06.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.ch06.domain.Article;
import com.springboot.ch06.dto.AddArticleRequest;
import com.springboot.ch06.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogService {
	private final BlogRepository blogRepository;
	
	//불로그 글 추가 메서드
	public Article save(AddArticleRequest request) {
		return blogRepository.save(request.toEntity());
	}
	
	public List<Article> findAll() {
		return blogRepository.findAll();
	}
	
	
	public Article findById(long id) {
		return blogRepository.findById(id)
				.orElseThrow(()->new IllegalArgumentException("not found:" + id));
	}
}
