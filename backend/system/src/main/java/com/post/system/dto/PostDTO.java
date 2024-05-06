package com.post.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO {
    private int id;
    private String title;
    private String bio;
    private String imagePath;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
