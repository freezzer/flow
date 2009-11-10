package de.ama.generator;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 05.11.2009
 * Time: 23:45:50
 * To change this template use File | Settings | File Templates.
 */
public class VisitorGenerate implements Visitor{
    public void visit(Tag visitor) {
        visitor.generate();
    }
}
