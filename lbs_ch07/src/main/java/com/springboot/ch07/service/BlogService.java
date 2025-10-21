package com.springboot.ch07.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.ch07.domain.Article;
import com.springboot.ch07.dto.AddArticleRequest;
import com.springboot.ch07.dto.UpdateArticleRequest;
import com.springboot.ch07.repository.BlogRepository;

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
	
	public void delete(long id) {
		blogRepository.deleteById(id);
	}
	
	@Transactional
	public Article update(long id, UpdateArticleRequest request) {
		Article article = blogRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("not fount:" + id));
		
		article.update(request.getTitle(), request.getContent());
		
		return article;
	}
}



