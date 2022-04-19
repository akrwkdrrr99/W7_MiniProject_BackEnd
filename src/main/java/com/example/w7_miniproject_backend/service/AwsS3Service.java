package com.example.w7_miniproject_backend.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.Metadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    @Value("myspartabucket1")
    private String bucket;

    private final AmazonS3 amazonS3;
    @Transactional
    public Map<String, String> uploadFile(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        if (multipartFile.getContentType().equals("multipart/form-data")
                && getFileExtension(multipartFile.getOriginalFilename()).equals(".jpg")) {
            objectMetadata.setContentType("image/jpg");
        }

        if (multipartFile.getContentType().equals("multipart/form-data")
                && getFileExtension(multipartFile.getOriginalFilename()).equals(".png")) {
            objectMetadata.setContentType("image/png");
        }
        //objectMetaData에 파라미터로 들어온 파일의 타입 , 크기를 할당.
        objectMetadata.setContentLength(multipartFile.getSize());

        //fileName에 파라미터로 들어온 파일의 이름을 할당.
        String fileName = multipartFile.getOriginalFilename();
        fileName = createFileName(fileName);
        try(InputStream inputStream = multipartFile.getInputStream()) {
            //amazonS3객체의 putObject 메서드로 db에 저장
            amazonS3.putObject(new PutObjectRequest(bucket, fileName , inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch(IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        Map<String , String> result = new HashMap<>();
        result.put("url" , String.valueOf(amazonS3.getUrl(bucket,fileName)));
        result.put("fileName" , fileName);
        return result;
    }

    public String deleteFile(String fileName) {
        DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
        amazonS3.deleteObject(request);
        return "삭제완료";
    }

    private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".png");
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)){
            System.out.println("idxFileName = " + idxFileName);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

}