package com.fileupload.service;

import com.fileupload.model.FileUploadModel;
import java.io.IOException;

public interface FileUploadService {

    void saveUploadedFile(FileUploadModel model) throws IOException;

}
