package com.monepic.gallery.util;


import static org.imgscalr.Scalr.OP_ANTIALIAS;
import static org.imgscalr.Scalr.OP_BRIGHTER;
import static org.imgscalr.Scalr.resize;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class ImageUtils {

    static Logger LOG = LoggerFactory.getLogger(ImageUtils.class);


    public static byte[] toThumb(Resource resource) {
        return resizeImg(resource, 125);
    }

    public static byte[] toMediumSize(Resource resource) {
        return resizeImg(resource, 550);
    }


    public static byte[] resizeImg(Resource resource, int size) {

        InputStream in = null;
        BufferedImage img = null;
        ByteArrayOutputStream out = null;

        try {

            img = resize(ImageIO.read(in = resource.getInputStream()), Method.BALANCED, size, OP_ANTIALIAS, OP_BRIGHTER);

            ImageIO.write(img, "PNG", out = new ByteArrayOutputStream());

            return out.toByteArray();

        } catch (IOException e) {
            LOG.error("Couldn't generate thumb", e);
            throw new RuntimeException(e);

        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

}
