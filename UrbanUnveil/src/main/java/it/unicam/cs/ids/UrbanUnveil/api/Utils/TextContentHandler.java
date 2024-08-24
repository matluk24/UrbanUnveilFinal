package it.unicam.cs.ids.UrbanUnveil.api.Utils;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class TextContentHandler implements ContentHandler {

    @Override
    public Content handleContent(Content content, MultipartFile file) throws IOException {
        String contentText = new String(file.getBytes(), StandardCharsets.UTF_8);
        long lineCount = contentText.lines().count();
        long wordCount = contentText.split("\\s+").length;

        return new TextContent(content, file.getOriginalFilename(), lineCount, wordCount, file.getSize());
    }
}

