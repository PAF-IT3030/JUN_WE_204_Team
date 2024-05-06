package com.post.system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusDTO {
    private int id;
    private String imagePath;

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
}
