package de.ama.framework.gui.frames {
import de.ama.framework.command.Invoker;
import de.ama.framework.data.Data;

public interface IEditor extends Invoker{
    function createData():Data;
}
}