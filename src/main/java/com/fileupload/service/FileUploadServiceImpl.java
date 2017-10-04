package com.fileupload.service;

import com.fileupload.model.FileUploadModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static String UPLOADED_FOLDER = "uploaded_files//";

    @Override
    public void saveUploadedFile(FileUploadModel model) throws IOException {

        // create the folder for uploaded files if not existing
        Path uploadedFolder = Paths.get(UPLOADED_FOLDER);
        if (!Files.exists(uploadedFolder)){
          Files.createDirectories(uploadedFolder);
        }

        // save the uploaded file
        MultipartFile file = model.getFile();
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);

        // write the meta data to file metadata.txt
        String metaData = model.toString();
        Path metaDataFile = Paths.get(UPLOADED_FOLDER + "metadata.txt");
        try {
          Files.createFile(metaDataFile);
        } catch (FileAlreadyExistsException ignored) {}
        Files.write(metaDataFile, metaData.getBytes(), StandardOpenOption.APPEND);
    }
}
