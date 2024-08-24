package it.unicam.cs.ids.UrbanUnveil.api.Utils;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;

public interface ContentHandler {
	 Content handleContent(Content content, MultipartFile file) throws IOException;
}
