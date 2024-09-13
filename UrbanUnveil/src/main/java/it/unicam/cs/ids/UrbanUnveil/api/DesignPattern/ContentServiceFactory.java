package it.unicam.cs.ids.UrbanUnveil.api.DesignPattern;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.ImageContent;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.models.VideoContent;
import it.unicam.cs.ids.UrbanUnveil.api.services.ContentService;
import it.unicam.cs.ids.UrbanUnveil.api.services.ImageContentService;
import it.unicam.cs.ids.UrbanUnveil.api.services.TextContentService;
import it.unicam.cs.ids.UrbanUnveil.api.services.VideoContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ContentServiceFactory {

    @Autowired
    private ImageContentService imageContentService;
    
    @Autowired
    private VideoContentService videoContentService;
    
    @Autowired
    private TextContentService textContentService;

	public ContentServiceFactory() {
		
	}

    public ContentService getService(Content content) {
    	
        if (content instanceof ImageContent) {
            return imageContentService;
        } else if (content instanceof VideoContent) {
            return videoContentService;
        } else if (content instanceof TextContent) {
            return textContentService;
        } else {
            throw new IllegalArgumentException("Unsupported content type");
        }
    }
    
    public ContentService getService(String fileType) {
    	if (fileType.startsWith("image")) {
            return imageContentService;
        } else if (fileType.startsWith("video")) {
            return videoContentService;
        } else if (fileType.startsWith("text")) {
            return textContentService;
        } else {
            throw new IllegalArgumentException("Unsupported file type");
        }
    }
}


