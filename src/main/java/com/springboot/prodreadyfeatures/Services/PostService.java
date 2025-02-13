package com.springboot.prodreadyfeatures.Services;

import com.springboot.prodreadyfeatures.DTO.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    ResponseEntity<PostDto> savePost(PostDto postDto);

    ResponseEntity<List<PostDto>> getAllPosts();

    ResponseEntity<PostDto> getPostById(long postId);

    ResponseEntity<PostDto> updatePost(PostDto postDto, long postId);
}
