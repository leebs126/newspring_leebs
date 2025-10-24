package com.springboot.ch09.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ch09.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
