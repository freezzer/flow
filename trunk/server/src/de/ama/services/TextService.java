package de.ama.services;

import de.ama.services.text.TextBaustein;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 09.01.2010
 * Time: 16:42:18
 * To change this template use File | Settings | File Templates.
 */
public interface TextService {
    public static final String NAME = "TextService";

    public List<TextBaustein> getTextBausteine(String key);


}
