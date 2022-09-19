package com.vladislavgoncharov.overlayforcounttimebeforedead.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapPictureStorageCloudinary {

    @Bean
    public static Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "developervlad",
                "api_key", "186944654822819",
                "api_secret", "6OvzVqdRBj-DZlcxhIH-wqP3zbE",
                "secure", true));
    }

}
