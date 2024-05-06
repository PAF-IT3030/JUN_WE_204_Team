package com.post.system.service;


import com.post.system.dto.StatusDTO;
import com.post.system.entity.Status;
import com.post.system.repo.StatusRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StatusService {

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private ModelMapper modelMapper;

    public StatusDTO insertStatus(StatusDTO statusDTO){
        statusRepo.save(modelMapper.map(statusDTO, Status.class));
        return statusDTO;
    }

    public List<StatusDTO> getAllStatus(){
        List<Status>statusList = statusRepo.findAll();
        return modelMapper.map(statusList, new TypeToken<List<StatusDTO>>(){}.getType());
    }

    public StatusDTO updateStatus(StatusDTO statusDTO){
        statusRepo.save(modelMapper.map(statusDTO, Status.class));
        return statusDTO;
    }

    public boolean deleteStatus(StatusDTO statusDTO){
        statusRepo.delete(modelMapper.map(statusDTO, Status.class));
        return true;
    }



}
