package de.ama.framework.data {
import de.ama.framework.gui.frames.Editor;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;

public class ModelDataProvider extends DataProvider {

    override public function getTable(cb:Callback) : void {
        callback = cb;
        var editor:Editor = Util.findParentEditor(invoker);
        var bo:BusinessObject = editor.getData();
        callback.execute(bo.getValue(path));
    }


}
}