package de.ama.services.impl;

import de.ama.services.GoogleService;
import de.ama.util.Util;
import org.stringtree.json.JSONReader;
import org.stringtree.json.JSONValidatingReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 28.07.2009
 * Time: 21:55:27
 * To change this template use File | Settings | File Templates.
 */
public class GoogleServiceImpl implements GoogleService {

    public GoogleServiceImpl() {
    }

    public List findFaces(String name, int limit) {
        return findPictures(name,imgtype_face,null,null,limit);
    }

    public List findPictures(String name, String type, String color, String size, int limit) {
        List ret = new ArrayList();
        try {
            int start = 0;
            boolean stop = false;
            while(ret.size()<limit && !stop){
                Object o = callGoogle( name, type, color, size, start);
                extractUrls((Map) o, ret);
                stop = (ret.size()<start+8);
                start+=8;
            }

            if(ret.size()>limit){
                ret = ret.subList(0, limit);
            }

        } catch (IOException e) {
             throw new RuntimeException("error in GoogleService.findPictures",  e);
        }

        return ret;
    }

    private Map callGoogle(String name, String type, String color, String size, int start) throws IOException {
        String query = URLEncoder.encode(name, "UTF-8");
        URL url = new URL("http://ajax.googleapis.com/ajax/services/search/images?" +
                "start=" + start +
                "&rsz=large" +
                "&v=1.0" +
                (Util.isEmpty(size) ? "": "&imgsz=" + size) +
                (Util.isEmpty(type) ? "": "&imgtype=" + type) +
                (Util.isEmpty(color) ? "": "&imgcolor=" + color) +
                "&q=" + query);

        URLConnection connection = url.openConnection();
        connection.addRequestProperty("Referer", "www.flow.com.");

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        String response = builder.toString();
        JSONReader jsonReader = new JSONValidatingReader();
        Object o = jsonReader.read(response);
        return (Map) o;
    }


    private void extractUrls(Map map, List ret) {
        if(map==null || map.size()==0) return;

        Map responseData = (Map) map.get("responseData");
        if(responseData==null || responseData.size()==0) return;

        List results = (List) responseData.get("results");
        if(results==null || results.size()==0) return;

        for (int i = 0; i < results.size(); i++) {
            Map result = (Map) results.get(i);
            ret.add(result.get("url"));
        }
    }

    public static void main(String[] args){
        GoogleService gs = new GoogleServiceImpl();
        String s = "Paris";
        List list = gs.findFaces(s, 63);
        Util.printList(s,list);
    }

}