package com.springboot.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ch07.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
