package com.springboot.prodreadyfeatures.Controller;
import com.springboot.prodreadyfeatures.DTO.PostDto;
import com.springboot.prodreadyfeatures.Services.PostService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/save")
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto)
    {
         return postService.savePost(postDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PostDto>> getAllPosts()
    {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long postId)
    {
        return postService.getPostById(postId);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto>  updatePost(@RequestBody PostDto postDto,@PathVariable long postId)
    {
        return postService.updatePost(postDto,postId);
    }
}
