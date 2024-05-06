package com.post.system.controller;

import com.post.system.FileUploadUtil;
import com.post.system.dto.PostDTO;
import com.post.system.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/api/v1/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/getPost")
    public List<PostDTO> getPost(){
        return postService.getAllPosts();
    }


    @PostMapping("/insertPost")
    public PostDTO insertPost(@RequestParam("title") String title,
                              @RequestParam("bio") String bio,
                              @RequestParam("files") MultipartFile[] files) {

        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(title);
        postDTO.setBio(bio);


        String imagePath = processAndSaveFiles(files);
        postDTO.setImagePath(imagePath);


        return postService.insertPost(postDTO);
    }

    private String processAndSaveFiles(MultipartFile[] files) {
        String uploadDir = "postImageFolder";
        String imagePath = null;
        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try {

                String filePath = FileUploadUtil.saveFile(uploadDir, fileName, file);
                if (imagePath == null) {
                    imagePath = filePath;
                }
            } catch (IOException ioException) {

            }
        }
        return imagePath;
    }


    @PutMapping("/updatePost")
    public PostDTO updatePost(@RequestBody PostDTO postDTO){
        return postService.updatePost(postDTO);
    }


    @DeleteMapping("/deletePost")
    @CrossOrigin(origins = "*")
    public boolean deletePost(@RequestBody PostDTO postDTO){
        return postService.deletePost(postDTO);
    }

}
