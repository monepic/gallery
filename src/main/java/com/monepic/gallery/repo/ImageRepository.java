package com.monepic.gallery.repo;

import org.springframework.data.repository.CrudRepository;

import com.monepic.gallery.obj.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {

}
