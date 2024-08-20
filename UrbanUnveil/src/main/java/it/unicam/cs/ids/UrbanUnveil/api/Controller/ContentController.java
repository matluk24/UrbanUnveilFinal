package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;

import it.unicam.cs.ids.UrbanUnveil.api.DesignPattern.ContentServiceFactory;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.ContentEnum;
import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.ImageContent;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.models.VideoContent;
import it.unicam.cs.ids.UrbanUnveil.api.services.ContentService;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentServiceFactory contentServiceFactory;
	private static final String UPLOAD_DIR = "./Utils/Content/";
    
    public ContentController(ContentServiceFactory o) {
    	contentServiceFactory=o;
	}
	
	public ContentController() {
		
	}
    


    @PostMapping("/uploadContent")
    public ResponseEntity<Content> handleContentUpload(@RequestBody Content content, MultipartFile file) throws IOException{
        if (file.isEmpty()) {
            return new ResponseEntity<Content>(content, HttpStatus.BAD_REQUEST);
        }

            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            File dest = null;


            // Gestione specifica basata sul tipo di file
            if (contentType != null) {
                if (contentType.startsWith("image/")) {
                	dest = new File(UPLOAD_DIR +"image/"+ fileName);
                } else if (contentType.startsWith("video/")) {
                	dest = new File(UPLOAD_DIR +"video/"+ fileName);
                } else if (contentType.startsWith("application/pdf")) {
                	dest = new File(UPLOAD_DIR +"file/"+ fileName);
                }
            }
            ContentService contentService = contentServiceFactory.getService(content);
            file.transferTo(dest);
            
            Content c = contentService.save(content);
            HttpStatus httpStatus = HttpStatus.CONFLICT;
            if(c.equals(content)) {
            	httpStatus = HttpStatus.OK;
            }
            return new ResponseEntity<Content>(c, httpStatus);
        }
       
    @GetMapping("/load")
    public ResponseEntity<Content> loadContent(@RequestBody Long id, ContentEnum contentEnum) throws IOException {
        // Logica per determinare il tipo di contenuto basato su contentEnum
        Content content = null;
        switch (contentEnum) {
            case IMAGE:
                content = new ImageContent();
                break;
            case VIDEO:
                content = new VideoContent();
                break;
            case TEXT:
                content = new TextContent();
                break;
        }
        ContentService contentService = contentServiceFactory.getService(content);
        
        Content c = contentService.load(id);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
         
        if(c.equals(content)) {
        	httpStatus = HttpStatus.OK;
        }
        
        return new ResponseEntity<Content>(c, httpStatus);
        	
        }
    
    @DeleteMapping("/delete")
    public  ResponseEntity<Content>  deleteContent(@RequestBody Long id, ContentEnum contentEnum) throws IOException {
        // Logica per determinare il tipo di contenuto basato su contentEnum
        Content content = null;
        switch (contentEnum) {
            case IMAGE:
                content = new ImageContent();
                break;
            case VIDEO:
                content = new VideoContent();
                break;
            case TEXT:
                content = new TextContent();
                break;
        }
        ContentService contentService = contentServiceFactory.getService(content);
        Content c = contentService.load(id);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
         
        if(c==null) {
        	httpStatus = HttpStatus.OK;
        }
        
        return new ResponseEntity<Content>(c, httpStatus);
        	
    }
    
    @PostMapping("/update")
    public ResponseEntity<Content> updateContent(@RequestBody Content content) throws IOException {
        ContentService contentService = contentServiceFactory.getService(content);
        
        Content c = contentService.update(content);
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        
        if(c.equals(content)) {
        	httpStatus = HttpStatus.OK;
        }else if (c == null)
			httpStatus = HttpStatus.BAD_REQUEST;
        
        return new ResponseEntity<Content>(c, httpStatus);
    }
    
}
