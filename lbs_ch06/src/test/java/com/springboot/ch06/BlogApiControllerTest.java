package com.springboot.ch06;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.ch06.domain.Article;
import com.springboot.ch06.dto.AddArticleRequest;
import com.springboot.ch06.dto.UpdateArticleRequest;
import com.springboot.ch06.repository.BlogRepository;
import com.springboot.ch06.service.BlogService;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogApiControllerTest {

	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	protected ObjectMapper objectMapper;  //직렬화, 역직렬화를 위한 클래스
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	BlogService blogService;
	
	@BeforeEach		//테스트 실행 전 실행하는 메서드
	public void mockMvcSetup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
						.build();
		blogRepository.deleteAll();
	}

	
	@DisplayName("addArticle: 블로그 글 추가 테스트")
	@Test
	public void addArticle() throws Exception {
		//given
		final String url = "/api/articles";
		final String title = "제목1";
		final String content = "내용1";
		final AddArticleRequest userRequest = new AddArticleRequest(title, content);
		
		
		//객체를 JSON으로 변환
		final String requestBody = objectMapper.writeValueAsString(userRequest);
		
		//when
		//설정한 내용을 바탕으로 요청 전송
		ResultActions result = mockMvc.perform(post(url)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(requestBody));
		
		
		//then
		result.andExpect(status().isCreated());
		
		List<Article> articles = blogRepository.findAll();
//		List<Article> articles = blogService.findAll();
		
		assertThat(articles.size()).isEqualTo(1);
		assertThat(articles.get(0).getTitle()).isEqualTo(title);
		assertThat(articles.get(0).getContent()).isEqualTo(content);
	}
	
	@DisplayName("findAllArticles : 모든 블로그글 조회")
	@Test
	public void findAllArticles() throws Exception {
		//given
		final String url ="/api/articles";
		final String title = "제목1";
		final String content = "내용1";
		
		blogRepository.save(Article.builder()
				.title(title)
				.content(content)
				.build());
	
		//when
		final ResultActions resultActions = mockMvc.perform(get(url)
				.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultActions.andExpect(status().isOk())
					 .andExpect(jsonPath("$[0].content").value(content))
					 .andExpect(jsonPath("$[0].title").value(title));
	
	}
	
	
	@DisplayName("findArticle : 블로그글 조회")
	@Test
	public void findArticle() throws Exception{
		//given
		final String url = "/api/articles/{id}";
		final String title = "제목2";
		final String content = "내용2";
		
		Article savedArticle = blogRepository.save(Article.builder()
				.title(title)
				.content(content)
				.build());
		
		//when
		final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));
		
		//then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value(content))
				.andExpect(jsonPath("$.title").value(title));
	}
	
	@DisplayName("deleteArticle: 블로그 글 삭제")
	@Test
	public void deleteArticle() throws Exception{
		//given
		final String url = "/api/articles/{id}";
		final String title = "제목1";
		final String content = "내용1";
		
		Article savedArticle = blogRepository.save(Article.builder()
				.title(title)
				.content(content)
				.build());
		
		//when
		mockMvc.perform(delete(url, savedArticle.getId()))
				.andExpect(status().isOk());
	
		//then
		List<Article> articles = blogRepository.findAll();
		assertThat(articles).isEmpty();
	
	}
	
	@DisplayName("updateArticle: 블로그글 수정하기")
	@Test
	public void updateArticle() throws Exception {
		//given
		final String url = "/api/articles/{id}";
		final String title = "title";
		final String content = "content";
		
		Article savedArticle = blogRepository.save(Article.builder()
				.title(title)
				.content(content)
				.build());
		
		final String newTitle = "new Title";
		final String newContent = "new Content";
		
		UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);
		
		//when
		ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request)));
		
		//then
		result.andExpect(status().isOk());
		
		Article article = blogRepository.findById(savedArticle.getId()).get();
		
		assertThat(article.getTitle()).isEqualTo(newTitle);
		assertThat(article.getContent()).isEqualTo(newContent);
		
		
	}
	
	
	
	
	
	
	
	
	
}
