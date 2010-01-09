package de.ama.framework.gui.fields {
import de.ama.framework.command.*;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;

public class EditFieldInvoker implements Invoker{
    private var _editField:EditField;

    public function EditFieldInvoker(ef:EditField) {
        this._editField = ef
    }

    public function get editField():EditField {
        return _editField;
    }

    public function getData():BusinessObject {    return null;  }
    public function setData(value:BusinessObject):void {   }
    public function getSelectionModel():SelectionModel {   return null;  }
}
}