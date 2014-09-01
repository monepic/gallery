package com.monepic.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="/api")
public class Api {

    public final static String API_NAME = "Gallery API";

    public final static String ROOT   = "api/";
    public final static String ALBUMS = "albums";
    public final static String IMAGES = "images";

    public final static Map<String, Class<?>> RESOURCE_MAP = new HashMap<String,Class<?>>();
    static {
        RESOURCE_MAP.put("self", Api.class);
        RESOURCE_MAP.put(ALBUMS, AlbumController.class);
        RESOURCE_MAP.put(IMAGES, ImageController.class);
    }


    static class ApiResource extends ResourceSupport {
        public final String name;
        public ApiResource(String name ) { this.name = name; }
    }

    @RequestMapping
    @ResponseBody ApiResource get(HttpServletRequest request) {

        ApiResource api = new ApiResource(API_NAME);

        for(Map.Entry<String, Class<?>> entry: RESOURCE_MAP.entrySet())
            api.add(linkTo(entry.getValue()).withRel(entry.getKey()));

        return api;
    }

}
