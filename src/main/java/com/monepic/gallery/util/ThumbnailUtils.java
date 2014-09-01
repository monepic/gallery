package com.monepic.gallery.util;


import static org.imgscalr.Scalr.OP_ANTIALIAS;
import static org.imgscalr.Scalr.OP_BRIGHTER;
import static org.imgscalr.Scalr.pad;
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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class ThumbnailUtils {

	static Logger LOG = LoggerFactory.getLogger(ThumbnailUtils.class);

	public static Resource getThumb(Resource resource) {

		InputStream in = null;
		BufferedImage img = null;
		ByteArrayOutputStream out = null;

		try {

			img = ImageIO.read(in = resource.getInputStream());
			img = createThumbnail(img);

			ImageIO.write(img, "PNG", out = new ByteArrayOutputStream());

			return new ByteArrayResource(out.toByteArray());

		} catch (IOException e) {
			LOG.error("Couldn't generate thumb", e);
			throw new RuntimeException(e);

		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}


	public static BufferedImage createThumbnail(BufferedImage img) {

		// Create quickly, then smooth and brighten it.
		return resize(img, Method.BALANCED, 125, OP_ANTIALIAS, OP_BRIGHTER);

		// Let's add a little border before we return result.
		
		
//		return pad(img, 4);

	}
}
