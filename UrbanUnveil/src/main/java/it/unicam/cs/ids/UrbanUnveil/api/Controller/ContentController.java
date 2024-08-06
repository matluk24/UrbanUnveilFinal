package it.unicam.cs.ids.UrbanUnveil.api.Controller;

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
    public void saveContent(@RequestBody Content content) {
        ContentService contentService = contentServiceFactory.getService(content);
        contentService.save(content);
      //TODO adattamento a ResponseEntity
    }

    @GetMapping("/load")
    public Content loadContent(@RequestBody Long id, ContentEnum contentEnum) {
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
        return contentService.load(id);
        
        //TODO adattamento a ResponseEntity
    }
    @DeleteMapping("/delete")
    public void deleteContent(@RequestBody Long id, ContentEnum contentEnum) {
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
        contentService.delete(id);
      //TODO adattamento a ResponseEntity
    }
}
