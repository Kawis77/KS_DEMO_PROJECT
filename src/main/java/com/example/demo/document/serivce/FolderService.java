package com.example.demo.document.serivce;

import com.example.demo.document.dao.entity.FolderEntity;
import com.example.demo.document.dao.repository.FolderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    public FolderEntity create(FolderEntity folderEntity){
        if (folderEntity != null){
            log.debug("Folder created successfully");
           return folderRepository.save(folderEntity);
        }else {
            log.error("Failed to create folder");
            return null;
        }
    }

    public void delete(Long id){
        if (id != null){
            folderRepository.deleteById(id);
            log.debug("Folder deleted successfully");
        }else {
            log.error("Failed to delete folder");
        }
    }

    public FolderEntity update(FolderEntity folderEntity){
        if (folderEntity != null){
            log.debug("Folder updated successfully");
           return folderRepository.save(folderEntity);

        }else {
            log.error("Failed to update folder");
            return null;
        }
    }
}
