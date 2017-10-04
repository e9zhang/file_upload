package com.fileupload;

import com.fileupload.model.FileUploadModel;
import com.fileupload.service.FileUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileUploadService fileUploadService;

    @Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "test file".getBytes());
        FileUploadModel model = new FileUploadModel();
        //model.setId("123");
        model.setFile(multipartFile);
        this.mvc.perform(fileUpload("/api/upload").file(multipartFile))
                .andExpect(status().isOk());

        //then(this.fileUploadService).should().saveUploadedFile(model);
    }

}
