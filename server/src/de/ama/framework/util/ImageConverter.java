package de.ama.framework.util;

import de.ama.util.Log;

import javax.imageio.*;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ImageConverter {


             
    public static void writeCompressedAndScaledJpeg(InputStream instream, File outfile, int width, int height, float quality ) throws IOException {

        ImageReader imageReader;
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(instream);
        Iterator readers = ImageIO.getImageReaders(imageInputStream);
        if (readers.hasNext()) {
            imageReader = (ImageReader) readers.next();
        } else {
            throw new RuntimeException("this seems not to be an Imageformat ");
        }
        imageReader.setInput(imageInputStream, true);
        BufferedImage input = imageReader.read(0);

        BufferedImage resized = getTiledInstance(input, width, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("jpeg");
        if (!writers.hasNext()) throw new IllegalStateException("No writers found");
        ImageWriter writer = writers.next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);

        FileImageOutputStream output = new FileImageOutputStream(outfile);
        writer.setOutput(output);
        writer.write(null, new IIOImage(resized, null, null), param);
    }

    public static void writeCompressedJpeg(InputStream inStream, File outfile, float quality) throws IOException {
        ImageReader imageReader;
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(inStream);
        Iterator readers = ImageIO.getImageReaders(imageInputStream);
        if (readers.hasNext()) {
            imageReader = (ImageReader) readers.next();
        } else {
            throw new RuntimeException("this seems not to be an Imageformat ");
        }
        imageReader.setInput(imageInputStream, true);

        BufferedImage input = imageReader.read(0);
        // Get a ImageWriter for jpeg format.
        Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("jpeg");
        if (!writers.hasNext()) throw new IllegalStateException("No writers found");
        ImageWriter writer = writers.next();
        // Create the ImageWriteParam to compress the image.
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);


        FileImageOutputStream output = new FileImageOutputStream(outfile);
        writer.setOutput(output);
        writer.write(null, new IIOImage(input, null, null), param);
    }


        /**
        * Convenience method that returns a scaled instance of the
        * provided {@code BufferedImage}.
        *
        * @param img the original image to be scaled
        * @param targetWidth the desired width of the scaled instance,
        *    in pixels
        * @param targetHeight the desired height of the scaled instance,
        *    in pixels
        * @param hint one of the rendering hints that corresponds to
        *    {@code RenderingHints.KEY_INTERPOLATION} (e.g.
        *    {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
        *    {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
        *    {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
        * @param higherQuality if true, this method will use a multi-step
        *    scaling technique that provides higher quality than the usual
        *    one-step technique (only useful in downscaling cases, where
        *    {@code targetWidth} or {@code targetHeight} is
        *    smaller than the original dimensions, and generally only when
        *    the {@code BILINEAR} hint is specified)
        * @return a scaled version of the original {@code BufferedImage}
        */
       public static BufferedImage getScaledInstance(BufferedImage img,
                                              int targetWidth,
                                              int targetHeight,
                                              Object hint,
                                              boolean higherQuality)
       {
           int type = (img.getTransparency() == Transparency.OPAQUE) ?
               BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
           BufferedImage ret = (BufferedImage)img;
           int w, h;
           if (higherQuality) {
               // Use multi-step technique: start with original size, then
               // scale down in multiple passes with drawImage()
               // until the target size is reached
               w = img.getWidth();
               h = img.getHeight();
           } else {
               // Use one-step technique: scale directly from original
               // size to target size with a single drawImage() call
               w = targetWidth;
               h = targetHeight;
           }

           do {
               if (higherQuality && w > targetWidth) {
                   w /= 2;
                   if (w < targetWidth) {
                       w = targetWidth;
                   }
               }

               if (higherQuality && h > targetHeight) {
                   h /= 2;
                   if (h < targetHeight) {
                       h = targetHeight;
                   }
               }

               BufferedImage tmp = new BufferedImage(w, h, type);
               Graphics2D g2 = tmp.createGraphics();
               g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
               g2.drawImage(ret, 0, 0, w, h, null);
               g2.dispose();

               ret = tmp;
           } while (w != targetWidth || h != targetHeight);

           return ret;
       }

    public static BufferedImage getTiledInstance(BufferedImage img,
                                                  int targetWidth,
                                                  Object hint ) {
        int type = (img.getTransparency() == Transparency.OPAQUE) ?
                BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret =  img;

        int w = img.getWidth();
        int h = img.getHeight();
        Log.write("initial image width("+w+")");

        while (w > targetWidth) {
            w /= 2;
            h /= 2;
            if (w < targetWidth) {
                break;
            }

            Log.write("scale image down to width("+w+") ");
            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();
            ret = tmp;
        }

        return ret;
    }


}

