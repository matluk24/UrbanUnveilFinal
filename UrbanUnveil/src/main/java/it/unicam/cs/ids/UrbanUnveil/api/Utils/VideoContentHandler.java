package it.unicam.cs.ids.UrbanUnveil.api.Utils;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.VideoContent;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class VideoContentHandler implements ContentHandler {

    @Override
    public Content handleContent(Content content, MultipartFile file) throws IOException {
        Tika tika = new Tika();
        Metadata metadata = new Metadata();

        tika.parse(file.getInputStream(), metadata);

        String contentType = metadata.get(Metadata.CONTENT_TYPE);
        String duration = metadata.get("xmpDM:duration");
        String frameRate = metadata.get("xmpDM:videoFrameRate");
        String width = metadata.get("tiff:ImageWidth");
        String height = metadata.get("tiff:ImageLength");

        return new VideoContent(content, file.getOriginalFilename(), contentType, duration, frameRate, width, height);
    }
}

