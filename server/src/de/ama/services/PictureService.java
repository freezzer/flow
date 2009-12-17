package de.ama.services;

import de.ama.services.picture.Picture;
import de.ama.services.user.User;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 04.06.2009
 * Time: 23:04:41
 * To change this template use File | Settings | File Templates.
 */
public interface PictureService {
    String NAME = "PictureService";

    public void storePicture(User user, FileItem fileItem, String description) ;
    public List findPictures(User user);
    public void deletePicture(String pictureId);
    public Picture findPicture(String id);
    public Picture findPictureByThumb(String thumbName);
}
