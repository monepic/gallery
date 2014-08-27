package com.monepic.gallery.service;

import java.util.UUID;

import com.monepic.gallery.obj.Image;

public interface ImageService {

	Image getImageById(UUID id);
}
