package com.springboot.ch08.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ch08.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
