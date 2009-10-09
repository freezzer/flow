package de.ama.services.impl;

import de.ama.db.Query;
import de.ama.framework.util.ImageConverter;
import de.ama.services.Environment;
import de.ama.services.PictureService;
import de.ama.util.Ini;
import de.ama.util.Log;
import de.ama.util.Util;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 04.06.2009
 * Time: 23:04:05
 * To change this template use File | Settings | File Templates.
 */
public class PictureServiceImpl implements PictureService {
    public static String PICTURE_NUMBER = "picture_number";

    public PictureServiceImpl() {
        Environment.getPersistentService().createSequenze(PICTURE_NUMBER);
    }

    public void storePicture(User user, FileItem fileItem, String description){
        Picture picture = new Picture();
        picture.setOwner(user);
        picture.setDescription(description);
        String context = Ini.getString("server.context","/flow"       ,"http context/doc-base of this server");

        long num = Environment.getPersistentService().getNextNumber(PICTURE_NUMBER);
        String fname = ""+ num +"_"+fileItem.getName();
        File file = de.ama.util.Environment.getFile("data/"+context+"/public/"+user.getName() , fname);

        String thumbName = "Thumb_"+num+"_"+fileItem.getName();
        File thumb = de.ama.util.Environment.getFile("data/"+context+"/public/"+user.getName() , thumbName);

        picture.setFileName("public/"+user.getName()+"/"+fname);
        picture.setThumbName("public/"+user.getName()+"/"+thumbName);

        try {
            ImageConverter.writeCompressedAndScaledJpeg(fileItem.getInputStream() , file, 800,800, 1f);
            Log.write("stored picture "+picture.getFileName());

            ImageConverter.writeCompressedAndScaledJpeg(fileItem.getInputStream() , thumb, 100,100, 0.9f);
            Log.write("stored thumb "+picture.getFileName());

        } catch (Exception e) {
            throw new RuntimeException("storePiture failed : ",e);
        }
        Environment.getPersistentService().makePersistent(picture);
    }

    public void deletePicture(String pictureId) {
        Picture picture = (Picture) Environment.getPersistentService().getObject(pictureId);
        if(picture == null){     return ;  }  // =================================>

        String context = Ini.getString("server.context","/flow"       ,"http context/doc-base of this server");
        File file = de.ama.util.Environment.getFile("data/"+context ,picture.getFileName() );
        if(file.exists()){
            file.delete();
        }
        File thumb = de.ama.util.Environment.getFile("data/"+context ,picture.getThumbName() );
        if(thumb.exists()){
            thumb.delete();
        }

        Environment.getPersistentService().delete(picture);
    }


    public List findPictures(User user) {
        Query q = new Query(Picture.class, "owner" , Query.EQ, user);
        List ret = Environment.getPersistentService().getObjects(q);
        return ret;
    }

    public Picture findPicture(String id) {
        if(Util.isEmpty(id)) return null;
        return (Picture) Environment.getPersistentService().getObject(id);
    }

    public Picture findPictureByThumb(String thumbName) {
        if(Util.isEmpty(thumbName)) return null;
        return (Picture) Environment.getPersistentService().getObject(new Query(Picture.class,"thumbName",Query.EQ, thumbName),false);
    }


}
