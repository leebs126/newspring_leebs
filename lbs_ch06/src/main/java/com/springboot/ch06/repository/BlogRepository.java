package com.springboot.ch06.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ch06.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
