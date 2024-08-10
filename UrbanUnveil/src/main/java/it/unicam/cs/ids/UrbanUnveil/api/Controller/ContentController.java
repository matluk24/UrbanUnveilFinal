package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    
    @PostMapping("/save")
    public ResponseEntity<String> saveContent(@RequestBody Content content) throws IOException {
        ContentService contentService = contentServiceFactory.getService(content);
        
        return contentService.save(content);
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
        if (c == null) {
        	return new ResponseEntity<Content>(c, HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<Content>(c, HttpStatus.OK);
        
        //TODO adattamento a ResponseEntity
    }
    @DeleteMapping("/delete")
    public  ResponseEntity<String>  deleteContent(@RequestBody Long id, ContentEnum contentEnum) throws IOException {
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
        return contentService.delete(id);
      //TODO adattamento a ResponseEntity
    }
}
