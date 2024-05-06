package com.post.system.service;

import com.post.system.dto.PostDTO;
import com.post.system.entity.Post;
import com.post.system.repo.PostRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    public PostDTO insertPost(PostDTO postDTO){
        postRepo.save(modelMapper.map(postDTO, Post.class));
        return postDTO;
    }

    public List<PostDTO> getAllPosts(){
        List<Post>postList=postRepo.findAll();
        return modelMapper.map(postList, new TypeToken<List<PostDTO>>(){}.getType());
    }

    public PostDTO updatePost(PostDTO postDTO){
        postRepo.save(modelMapper.map(postDTO, Post.class));
        return postDTO;
    }

    public boolean deletePost(PostDTO postDTO){
        postRepo.delete(modelMapper.map(postDTO, Post.class));
        return true;
    }
}
