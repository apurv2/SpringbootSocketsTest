package meetme.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppIwthInjectionConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", "duf1ntj7z");
        config.put("api_key", "647816789382186");
        config.put("api_secret", "5R3U1Oc9zwvnPOfI-TtlIeI0u_E");
        return new Cloudinary(config);
    }
}
