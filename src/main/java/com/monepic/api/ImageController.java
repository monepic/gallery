package com.monepic.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monepic.gallery.obj.Image;
import com.monepic.gallery.repo.ImageRepository;
import com.monepic.gallery.resource.AlbumResource;
import com.monepic.gallery.resource.ImageResource;


@Controller
@RequestMapping(Api.ROOT + Api.IMAGES)
@ExposesResourceFor(AlbumResource.class)
public class ImageController {

    @Autowired ImageRepository imageRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<ImageResource> images() {

        return ImageResource.fromImages(imageRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ImageResource> get(@PathVariable long id) {

        Image image = imageRepository.findOne(id);

        if (image == null) { return new ResponseEntity<ImageResource>(HttpStatus.NOT_FOUND); }

        return new ResponseEntity<ImageResource>(ImageResource.fromImage(image), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}/src", method = RequestMethod.GET, produces = "image/jpeg")
    public @ResponseBody Resource getSrc(@PathVariable long id) {
        return imageRepository.findOne(id).getResource();
    }

    @RequestMapping(value="{id}/thumb", method = RequestMethod.GET, produces = "image/jpeg")
    public @ResponseBody Resource getThumb(@PathVariable long id) {
        return imageRepository.findOne(id).getThumbnail();
    }

    @RequestMapping(value="{id}/medium", method = RequestMethod.GET, produces = "image/jpeg")
    public @ResponseBody Resource getMedium(@PathVariable long id) {
        return imageRepository.findOne(id).getMedium();
    }

}
