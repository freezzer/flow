package de.ama.services;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 28.07.2009
 * Time: 21:52:30
 * To change this template use File | Settings | File Templates.
 */
public interface GoogleService {
    public static final String NAME = "GoogleService";

    public static String imgcolor_black ="black";
    public static String imgcolor_blue  ="blue";
    public static String imgcolor_brown ="brown";
    public static String imgcolor_gray  ="gray";
    public static String imgcolor_green ="green";
    public static String imgcolor_orange="orange";
    public static String imgcolor_pink  ="pink";
    public static String imgcolor_purple="purple";
    public static String imgcolor_red   ="red";
    public static String imgcolor_teal  ="teal";
    public static String imgcolor_white ="white";
    public static String imgcolor_yellow="yellow";

    public static String imgc_gray="gray";
    public static String imgc_color="color";

    public static String imgsize_small="icon";
    public static String imgsize_medium="medium";
    public static String imgsize_large="xxlarge";
    public static String imgsize_huge="huge";

    public static String imgtype_face="face";
    public static String imgtype_phote="photo";
    public static String imgtype_clipart="clipart";
    public static String imgtype_lineart="lineart";

    public List findFaces(String name, int limit);
    public List findPictures(String name, String type, String color, String size, int limit);

}
