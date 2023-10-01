package com.example.demo.files.service;

import com.example.demo.files.dao.FileConstants;
import com.example.demo.files.dao.entity.FileEntity;
import com.example.demo.files.dao.repository.FileRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.example.demo.document.constans.DocumentConstants.*;
import static com.example.demo.document.constans.DocumentConstants.DOCUMENT_EXTENSION_TXT;

@Log4j2
@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileEntity saveFile(FileEntity file) {
        if (file != null) {
            return fileRepository.save(file);
        } else {
            log.debug("The attempt to save the file is not possible, the file is null");
        }
        return null;
    }

    public void deleteFile(Long id){
        if (id != null){
            fileRepository.deleteById(id);
            log.debug("File deleted successfully");
        }
    }

    public String saveFileOnServer(MultipartFile multipartFile){
        String extension = "";
        if (multipartFile != null && !multipartFile.getName().isEmpty()) {
            try {
                Path pathDirectory = Paths.get(FileConstants.REGULAR_FILE_PATH);
                if (!Files.exists(pathDirectory)) {
                    Files.createDirectories(pathDirectory);
                }
                String documentType = multipartFile.getContentType();

                if (documentType.equals(DOCUMENT_CONTENT_PDF)) {
                    extension = DOCUMENT_EXTENSION_PDF;
                }else if (documentType.equals(DOCUMENT_CONTENT_JPEG)) {
                    extension = DOCUMENT_EXTENSION_JPEG;
                }else if (documentType.equals(DOCUMENT_CONTENT_PNG)) {
                    extension = DOCUMENT_EXTENSION_PNG;
                }else if (documentType.equals(DOCUMENT_CONTENT_DOCX)){
                    extension = DOCUMENT_EXTENSION_DOCX;
                }else if (documentType.equals(DOCUMENT_CONTENT_XLSX)){
                    extension = DOCUMENT_EXTENSION_XLSX;
                }else if (documentType.equals(DOCUMENT_CONTENT_CSV)){
                    extension = DOCUMENT_EXTENSION_CSV;
                }else if(documentType.equals(DOCUMENT_CONTENT_TXT)) {
                    extension = DOCUMENT_EXTENSION_TXT;
                }else{
                    log.info("Problem with create file , type of file is wrong");
                    return null;
                }

                Path targetLocationRegularFile = Path.of(FileConstants.REGULAR_FILE_PATH , System.currentTimeMillis() + extension);
                Files.copy(multipartFile.getInputStream() , targetLocationRegularFile , StandardCopyOption.REPLACE_EXISTING);
                File file = targetLocationRegularFile.toFile();

                if (file != null){
                    return file.getName();
                }
            } catch (IOException e) {
                log.error("Problem with save file on server.  ERROR: " + e);
            }
        }
        return null;
    }
}
