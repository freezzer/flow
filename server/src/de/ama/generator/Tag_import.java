/*
    This file is part of flow xml-model based app-generator using java and flex .

    flow is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    flow is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
    
*/


package de.ama.generator;

/**
 * User: x
 * Date: 16.06.2008
 */
public class Tag_import extends Tag{

    public void prepareElement() {
        String filename = getRequiredAttribute("file");
        try {
            Tag tag = getWriter().readTemplateFile(filename);
            tag.prepareRecursive();
            addChild(tag);
        } catch (Exception e) {
            throw new RuntimeException("can not read import ["+ filename+"]", e);
        }
    }
}