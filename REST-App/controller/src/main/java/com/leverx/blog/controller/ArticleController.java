package com.leverx.blog.controller;

import com.leverx.blog.dto.ArticleDTO;
import com.leverx.blog.dto.SearchCriteriaDTO;
import com.leverx.blog.dto.transfer.New;
import com.leverx.blog.dto.transfer.Update;
import com.leverx.blog.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticleController {

    private static final String ROLE_USER = "ROLE_USER";
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getArticles(SearchCriteriaDTO searchCriteriaDTO) {
        return ResponseEntity.ok(articleService.getArticles(searchCriteriaDTO));
    }

    @PostMapping
    @Secured(ROLE_USER)
    public ResponseEntity<ArticleDTO> createArticle(@Validated(New.class) @RequestBody ArticleDTO articleDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(authentication.getName(), articleDTO));
    }

    @PutMapping("/{id}")
    @Secured(ROLE_USER)
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable int id,
                                                    @Validated(Update.class) @RequestBody ArticleDTO articleDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        articleDTO.setId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.updateArticle(authentication.getName(), articleDTO));
    }

    @GetMapping("/my")
    @Secured(ROLE_USER)
    public ResponseEntity<List<ArticleDTO>> getOwnArticles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity
                .ok(articleService.getOwnArticles(authentication.getName()));
    }

    @DeleteMapping("/{id}")
    @Secured(ROLE_USER)
    public void deleteArticle(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        articleService.deleteArticle(authentication.getName(), id);
    }
}
