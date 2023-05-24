package com.example.demo.document.serivce;

import com.aspose.words.Document;
import com.aspose.words.HtmlLoadOptions;
import com.aspose.words.SaveFormat;
import com.example.demo.document.dao.form.RegularDocumentDataForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class RegularDocumentService {

    private static final Logger log = LogManager.getLogger(RegularDocumentService.class);

    public File createRegularDocumentContent(RegularDocumentDataForm regularDocumentDataForm) {
        File createdFile = null;

        try {

            if (regularDocumentDataForm.getContent() != null && regularDocumentDataForm.getTitle() != null) {


                String html = regularDocumentDataForm.getContent();
                String title = regularDocumentDataForm.getTitle() + ".docx";

                String locationForDoc = "D:\\Projects\\KS_DEMO_PROJECT\\target\\documents\\" + title;

                InputStream htmlStream = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));
                HtmlLoadOptions loadOptions = new HtmlLoadOptions();

                Document doc = new Document(htmlStream, loadOptions);

                // Zapisanie dokumentu w formacie docx
                doc.save(locationForDoc, SaveFormat.DOCX);

                createdFile = new File(locationForDoc);

                if (createdFile.exists()) {
                    log.debug("File with name : " + createdFile.getName() + " is created ");

                    return createdFile;
                }
            } else {
                log.error("Title or content for document is null ");
                return createdFile;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createdFile;

    }


}