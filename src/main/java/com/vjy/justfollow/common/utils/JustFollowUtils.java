package com.vjy.justfollow.common.utils;

import com.vjy.justfollow.common.response.IOResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
@Scope(value = "singleton")
public class JustFollowUtils {

    private static final String BASE_PATH = "F:/cp";
    private static final String URL_PRIFIX = "http://192.172.4.122:8080/api/user/img/";
    private static final String URL_POST_PRIFIX = "http://192.172.4.122:8080/api/user/post/img/";






    public IOResponse saveFile(MultipartFile file, String fileName) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File(BASE_PATH, fileName)));
                buffStream.write(bytes);
                buffStream.close();


                return new IOResponse(IOResponse.Status.SUCCESS,"You have successfully uploaded", fileName);

            }catch (IOException e) {
                return new IOResponse(IOResponse.Status.FAIELD,"You failed to upload", e.getMessage());
            }
        }else {
            return new IOResponse(IOResponse.Status.FAIELD,"You failed to upload", "Unable to upload. File is empty");
        }

    }



    public IOResponse savePostFile(MultipartFile file, String fileName, String userId) {

        if (!file.isEmpty()) {

            File fileDir = new File(BASE_PATH + "\\" + userId);
            if (!fileDir.exists()) {
                if (fileDir.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }


            try {
                byte[] bytes = file.getBytes();

                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File(fileDir, fileName)));
                buffStream.write(bytes);
                buffStream.close();


                return new IOResponse(IOResponse.Status.SUCCESS,"You have successfully uploaded", fileName);

            }catch (IOException e) {
                return new IOResponse(IOResponse.Status.FAIELD,"You failed to upload", e.getMessage());
            }
        }else {
            return new IOResponse(IOResponse.Status.FAIELD,"You failed to upload", "Unable to upload. File is empty");
        }

    }



    public static String getFileUrl(String fileName) {

        return URL_PRIFIX + fileName;
    }


    public static String getPostFileUrl(String userId, String fileName) {

        return URL_POST_PRIFIX + userId + "/" + fileName;
    }




    public ResponseEntity<Object> getFile(String fileName){

        File initialFile = new File(BASE_PATH, fileName);
        InputStream targetStream = null;
        try {
            targetStream = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(targetStream));

    }


    public ResponseEntity<Object> getPostFile(String userId, String fileName){

        File initialFile = new File(BASE_PATH + "/" + userId, fileName);
        InputStream targetStream = null;
        try {
            targetStream = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(targetStream));

    }

}
