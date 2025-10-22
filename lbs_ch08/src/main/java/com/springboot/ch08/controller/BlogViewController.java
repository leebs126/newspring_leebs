package com.springboot.ch08.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.ch08.domain.Article;
import com.springboot.ch08.dto.ArticleListViewResponse;
import com.springboot.ch08.dto.ArticleViewResponse;
import com.springboot.ch08.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
	
	private final BlogService blogService;
	
	@GetMapping("/articles")
	public String getArticles(Model model) {
	    List<ArticleListViewResponse> articles = blogService.findAll().stream()
	    		.map(ArticleListViewResponse::new)
		        .sorted(Comparator.comparing(ArticleListViewResponse::getId).reversed()) // 🔽 id 기준 내림차순
		        .toList();

		
		model.addAttribute("articles", articles);
		return "articleList";
	}
	
	@GetMapping("/articles/{id}")
	public String getArticle(@PathVariable("id") Long id, Model model) {
		Article article = blogService.findById(id);
		model.addAttribute("article", new ArticleViewResponse(article));
		
		return "article";
	}
	
	@GetMapping("/new-article")
	//id 키를 가진쿼리 파라미터의 값을 id 변수에 매핑(id는 없을 수도 있음)
	public String newArticle(@RequestParam(name="id", required=false) Long id, Model model) {
		if(id == null) {  //id가 없으면
			model.addAttribute("article", new ArticleViewResponse());
		} else {  //id가 있으면 수정
			Article article = blogService.findById(id);
			model.addAttribute("article", new ArticleViewResponse(article));
		}
		return "newArticle";
	}
	
	

	
	
	
	
}


