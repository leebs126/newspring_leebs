package com.springboot.ch08.dto;

import java.time.LocalDateTime;

import com.springboot.ch08.domain.Article;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	
	
	public ArticleViewResponse(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
	}
}
