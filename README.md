# file_upload

1. Bring up the application by running './gradlew bootRun' from the project root
2. Upload a file by running below command from another command line prompt

        curl -F id="<fileId>" -F file=@"<filePath>" http://localhost:8080/api/upload
3. If uploading is successful, 'Successfully uploaded - fileName' displays on the command prompt
4. The uploaded file is saved in uploaded_files/ with the same file name
5. The id and name of the uploaded file are saved to uploaded_files/metadata.txt file
