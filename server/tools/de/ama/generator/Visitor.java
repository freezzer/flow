package de.ama.generator;


/**
 * User: Andreas Marochow
 * Ein Visitor traversiert auf dem Model ueber Knoten, und fuehrt gegebenenfalls seine visit(Tag tag) Methode aus.
 * In dieser visit() Methode kann output fuer Artefakte erzeugt werden.
 *
 * Als Anonyme-Inline-Implementierung kann der Code aber im aufrufenden Tag (Knoten) stehen, was die generierenden Code
 * stellen auf den initierenden Tags (Knoten des Models) zusammenhaelt.
 *
 * Beispiel:
 *         visitChildren(COMMAND, new Visitor() {
            public void visit(Tag command) {
                writeLine();
                write("        cmd = new " + getFullClassName(command.getRequiredAttributeAlternative(USE, NAME)) + "();");
                write("        commands.add(cmd);");
            }
        }, false);
 *
 *
 */
public interface Visitor {
       public void visit(Tag visitor);
}
