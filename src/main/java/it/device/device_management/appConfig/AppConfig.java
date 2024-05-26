package it.device.device_management.appConfig;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"it.device.device_management"})
public class AppConfig {

    @Bean
    public Cloudinary cloudinary(@Value("${cloudinary.name}") String cloudName,
                                 @Value("${cloudinary.apikey}") String apiKey,
                                 @Value("${cloudinary.secret}") String apiSecret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }


}
