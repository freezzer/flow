package de.ama.generator;


/**
 * User: Andreas Marochow
 * Ein Visitor traversiert auf dem Model ueber Knoten, und fuehrt gegebenenfalls seine visit(Tag tag) Methode aus.
 * In dieser visit() Methode kann output fuer Artefakte erzeugt werden.
 *
 * Dieser Visitor ruft die generate Methode des besuchten childs auf. Der Besuchte Knoten liefert in seiner generate
 * Methode dann in der Regel den Standard-Code.
 *
 * Man koennte auf die generate Methode verzichten und nur spezielle Vistoren (inline) schreiben.
 *
 * @see de.ama.generator.Visitor
 */

public class VisitorGenerate implements Visitor{
    public void visit(Tag visitor) {
        visitor.generate();
    }
}
