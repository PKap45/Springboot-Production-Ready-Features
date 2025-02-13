package com.springboot.prodreadyfeatures.Services.ServiceImpl;

import com.springboot.prodreadyfeatures.DTO.PostDto;
import com.springboot.prodreadyfeatures.Entity.PostEntity;
import com.springboot.prodreadyfeatures.Exception.ResourceNotFound;
import com.springboot.prodreadyfeatures.Repository.PostRepository;
import com.springboot.prodreadyfeatures.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<PostDto> savePost(PostDto postDto) {
        PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);
        postRepository.save(postEntity);
        PostDto dto = modelMapper.map(postEntity, PostDto.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostEntity> listofPosts = postRepository.findAll();
        List<PostDto> dtos = listofPosts.stream().map(
                posts -> {
                    return modelMapper.map(posts, PostDto.class);
                }
        ).collect(Collectors.toList());
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> getPostById(long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post Not Found With id" + postId)
        );
        PostDto dto = modelMapper.map(postEntity, PostDto.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> updatePost(PostDto postDto, long postId) {
        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post Not Found With id: " + postId)
        );
        PostEntity postEntity1 = modelMapper.map(postDto, PostEntity.class);
        postEntity1.setId(postId);
        PostEntity savedPost = postRepository.save(postEntity1);
        PostDto updatePost = modelMapper.map(savedPost, PostDto.class);
        return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }


}
