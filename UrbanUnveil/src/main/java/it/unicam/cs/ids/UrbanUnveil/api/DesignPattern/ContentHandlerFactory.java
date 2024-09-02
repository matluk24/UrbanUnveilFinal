package it.unicam.cs.ids.UrbanUnveil.api.DesignPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.unicam.cs.ids.UrbanUnveil.api.Utils.ContentHandler;

import java.util.Map;

@Component
public class ContentHandlerFactory {

	@Autowired
    private final Map<String, ContentHandler> handlers;

  
    public ContentHandlerFactory(Map<String, ContentHandler> handlers) {
        this.handlers = handlers;
    }

    public ContentHandler getHandler(String contentType) {
        if (contentType.startsWith("image/")) {
            return handlers.get("imageContentHandler");
        } else if (contentType.startsWith("video/")) {
            return handlers.get("videoContentHandler");
        } else if (contentType.startsWith("text/plain")) {
            return handlers.get("textContentHandler");
        }
        throw new IllegalArgumentException("Unsupported content type: " + contentType);
    }
}

