package de.ama.framework.servlet;

import de.ama.services.Environment;
import de.ama.services.user.User;
import de.ama.util.Ini;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class UploadServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/xml; charset=utf-8";

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException {
        response.setContentType(CONTENT_TYPE);

        FileItem fitem = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();                                    // We use the FileUpload package provided by Apache to process the request.
        ServletFileUpload upload = new ServletFileUpload(factory);

        String catalog = Ini.getString("db.catalog", "flow", "der default Catalog in Datenbank");
        String description = "uploaded file";
        String userSessionId = null;
        boolean onlyImage = false;

        try {
            List items = upload.parseRequest(request);
            for (int i = 0; i < items.size(); i++) {
                FileItem item = (FileItem) items.get(i);
                if (item.isFormField()) {
                    if (item.getFieldName().equalsIgnoreCase("catalog")) {
                        catalog = item.getString();
                    }
                    if (item.getFieldName().equalsIgnoreCase("description")) {
                        description = item.getString();
                    }
                    if (item.getFieldName().equalsIgnoreCase("userSessionId")) {
                        userSessionId = item.getString();
                    }
                    if (item.getFieldName().equalsIgnoreCase("onlyImage")) {
                        onlyImage = Boolean.parseBoolean(item.getString());
                    }
                } else {
                    fitem = item;
                }
            }

            if (onlyImage) {
                ImageReader imageReader = null;
                ImageInputStream imageInputStream = ImageIO.createImageInputStream(fitem.getInputStream());
                try {
                    Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
                    if (imageReaders.hasNext()) {                                                     // Get the next (only) image.
                        imageReader = imageReaders.next();
                    }
                    // Non-image files will throw a NullPointerException on the next line.
                    imageReader.getFormatName();
                } catch (IOException e) {
                    response.setHeader("format-error", " the file seems not to bee an image");
                } finally {
                    imageReader.dispose();
                    imageInputStream.close();
                }
            }


            User user = Environment.getUserService().getActiveUser(userSessionId);
            Environment.getPersistentService().join(catalog);
            Environment.getPictureService().storePicture(user, fitem, description);
            Environment.getPersistentService().commit();
            
        } catch (Exception e) {
          response.setHeader("Error", " file seems not to bee an image");
        } finally {
            Environment.getPersistentService().leave();
        }
    }
}
