package com.monepic.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monepic.gallery.obj.Image;
import com.monepic.gallery.resource.ImageResource;
import com.monepic.gallery.service.ImageService;


@Controller
@RequestMapping("/images")
public class ImageController {

	@Autowired ImageService imageService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ImageResource> get(@PathVariable UUID id) {

		Image image = imageService.getImageById(id);

		if (image == null) {
			return new ResponseEntity<ImageResource>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ImageResource>(ImageResource.fromImage(image), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/src", method = RequestMethod.GET)
	public @ResponseBody Resource getSrc(@PathVariable UUID id) {
		return imageService.getImageById(id).getResource();
	}
}
