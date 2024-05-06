package com.post.system.controller;

import com.post.system.FileUploadUtil;
import com.post.system.dto.StatusDTO;
import com.post.system.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/api/v1/status")
@CrossOrigin
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService){
        this.statusService = statusService;
    }

    @GetMapping("/getStatus")
    public List<StatusDTO> getStatus(){
        return statusService.getAllStatus();
    }

    @PostMapping("/insertStatus")
    public StatusDTO insertStatus(@RequestParam("files")MultipartFile[] files){
        StatusDTO statusDTO = new StatusDTO();
        String imagePath = processAndSaveFiles(files);
        statusDTO.setImagePath(imagePath);

        return statusService.insertStatus(statusDTO);

    }

    private String processAndSaveFiles(MultipartFile[] files){
        String uploadDir = "statusImageFolder";
        String imagePath = null;
        for (MultipartFile file : files){
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try{
                String filePath = FileUploadUtil.saveFile(uploadDir, fileName, file);
                if(imagePath == null){
                    imagePath = filePath;
                }
            }catch (IOException ioException){

            }
        }
        return  imagePath;
    }

    @PutMapping("/updateStatus")
    public StatusDTO updateStatus(@RequestBody StatusDTO statusDTO){
        return statusService.updateStatus(statusDTO);
    }

    @DeleteMapping("/deleteStatus")
    public boolean deleteStatus(@RequestBody StatusDTO statusDTO){
        return statusService.deleteStatus(statusDTO);
    }


}
