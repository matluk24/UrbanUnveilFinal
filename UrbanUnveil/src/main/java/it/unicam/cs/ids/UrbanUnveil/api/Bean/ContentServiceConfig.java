package it.unicam.cs.ids.UrbanUnveil.api.Bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.unicam.cs.ids.UrbanUnveil.api.services.ContentService;
import it.unicam.cs.ids.UrbanUnveil.api.services.ImageContentService;
import it.unicam.cs.ids.UrbanUnveil.api.services.TextContentService;
import it.unicam.cs.ids.UrbanUnveil.api.services.VideoContentService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ContentServiceConfig {

    @Bean
    public Map<String, ContentService> contentServiceMap(
            ImageContentService imageContentService,
            VideoContentService videoContentService,
            TextContentService textContentService) {

        Map<String, ContentService> serviceMap = new HashMap<>();
        serviceMap.put("imageContentService", imageContentService);
        serviceMap.put("videoContentService", videoContentService);
        serviceMap.put("textContentService", textContentService);

        return serviceMap;
    }
}
