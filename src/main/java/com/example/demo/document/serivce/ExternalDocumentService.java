package com.example.demo.document.serivce;


import com.example.demo.constans.DocumentConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.example.demo.constans.DocumentConstants.*;

@Log4j2
@Service
public class ExternalDocumentService {


    public File createExternalFile(MultipartFile multipartFile) {

        String directoryLocation = "D:\\Projects\\KS_DEMO_PROJECT\\target\\external-documents";
        String extension = "";

        if (multipartFile != null) {
            try {
                Path pathDirectory = Paths.get(directoryLocation);
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
                }else {
                    log.info("Problem with create file , type of file is wrong");
                    return null;
                }

                Path targetLocationExternalDoc = Path.of(directoryLocation , System.currentTimeMillis() + extension);
                Files.copy(multipartFile.getInputStream() , targetLocationExternalDoc , StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                log.error("Problem with create directory for external documents.  ERROR: " + e);
            }
        }
        return null;
    }

}
