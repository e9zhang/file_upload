package com.fileupload.controller;

import com.fileupload.model.FileUploadModel;
import com.fileupload.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class FileUploadController {

    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/api/upload")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute FileUploadModel model) {

        logger.debug("Upload file [" + model.getFile().getOriginalFilename()
                      + "] with id [" + model.getId() + "]");

        try {
            fileUploadService.saveUploadedFile(model);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                model.getFile().getOriginalFilename(), HttpStatus.OK);
    }
}
