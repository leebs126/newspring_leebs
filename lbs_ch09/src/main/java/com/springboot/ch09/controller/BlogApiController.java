package com.springboot.ch09.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ch09.domain.Article;
import com.springboot.ch09.dto.AddArticleRequest;
import com.springboot.ch09.dto.ArticleResponse;
import com.springboot.ch09.dto.UpdateArticleRequest;
import com.springboot.ch09.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController  //HTTP Response Body 객체에 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {
	private final BlogService blogService;
	
	//HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
	@PostMapping("/api/articles")
	//@RequestBody로 요청 시 본문 값 매핑
	public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
		Article savedArticle = blogService.save(request);
		
		//요청한 자원이 성공적으로 생성되었으면 저장된 블로그 글 정보를 응답 객체에 담아 전송
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedArticle);
	}
	
	@GetMapping("/api/articles")
	public ResponseEntity<List<ArticleResponse>> findAllArticles(){
		List<ArticleResponse> articles = blogService.findAll()
				.stream()
				.map(ArticleResponse::new)
				.toList();
		return ResponseEntity.ok()	
				.body(articles);
	}
	
	
	@GetMapping("/api/articles/{id}")
	public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") Long id){
		Article article = blogService.findById(id);
		
		return ResponseEntity.ok()
					.body(new ArticleResponse(article));
	}
	
	
	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id){
		blogService.delete(id);
		return ResponseEntity.ok()
				.build();
	}
	
	@PutMapping("/api/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id,
												@RequestBody UpdateArticleRequest request) {
		Article updateArticle = blogService.update(id, request);
		
		return ResponseEntity.ok()
				.body(updateArticle);
	}
}







