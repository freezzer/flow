package de.ama.services;

import de.ama.huyua.Picture;
import de.ama.huyua.Voting;
import de.ama.huyua.PictureData;
import de.ama.services.impl.User;
import org.apache.commons.fileupload.FileItem;

import java.util.List;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 04.06.2009
 * Time: 23:04:41
 * To change this template use File | Settings | File Templates.
 */
public interface DomainService {
    String NAME = "DomainService";

    public void storePicture(User user, FileItem fileItem, String description) ;
    public List findPictures(User user);
    public List findVotedPictures(User user);
    public Picture findPicture(String pictureId);
    public Picture findPictureByThumb(String thumbName);
    public void deletePicture(String pictureId);
    public List findNextPicturesToVote(User user, int limit);
    public List findTeasePictures(int limit, String oid);

    /////////////////////////////////////// Voting ///////////////////////////////////////

    public Voting findVoting(String id);
    public void deleteVoting(String votingid);
    public void storeVoting(Voting v,User user);
    public List findVotingsOfUser(User user);
//    public List getVoters(Picture picture);

    public List getVotingsOfPictureAndUser(Picture picture, User user);
    public List getVotingsOfPicture(Picture picture);
    public void recalcVotingSum(Picture pic);

}
