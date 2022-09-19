package com.vladislavgoncharov.overlayforcounttimebeforedead.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

@Component
public class AudioUpdate {

    private final Cloudinary cloudinary;

    public static String audioName = "/audio/start.mp3";

    public AudioUpdate(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public void saveAudio(MultipartFile multipartFile) throws IOException {

        File audio = new File("start.mp3");

        try (InputStream inputStreamReader = multipartFile.getInputStream();
             OutputStream outputStream = new FileOutputStream(audio)) {
             IOUtils.copy(inputStreamReader, outputStream);
        }

        Map uploadResult = cloudinary.uploader()
                .upload(audio, ObjectUtils.asMap("folder", "owerlay/musicStart","resource_type", "video"));

        audio.delete();

        AudioUpdate.audioName = String.valueOf(uploadResult.get("url"));
    }

}
