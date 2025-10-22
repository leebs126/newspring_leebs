package com.springboot.ch07.dto;

import java.time.LocalDateTime;

import com.springboot.ch07.domain.Article;

import lombok.Getter;

@Getter
public class ArticleResponse {
	private final String title;
	private final String content;
	private final LocalDateTime createdAt; // 🕒 작성일 추가
	
	
	public ArticleResponse(Article article) {
		this.title= article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt(); // Article 엔티티의 필드명과 동일하게
	}
}
