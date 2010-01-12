package de.ama.services.text {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.command.*;
import de.ama.framework.data.BoReference;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.fields.EditField;
import de.ama.framework.gui.fields.EditFieldInvoker;
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;

public class SelectTextBausteinCommand extends Command{
    private var _key:String;
    private var _start:int;
    private var _stop:int;
    private var _editField:EditField;

    public function SelectTextBausteinCommand(label:String = "Textbaustein ausw√§hlen", icon:String = "search") {
        super(label, icon);
    }

    override protected function execute():void {
        _editField = EditField(EditFieldInvoker(invoker).editField);
        var obj:Object = _editField.getTextAtCaret();

        if (obj != null) {
            _key = obj.key;
			if(!Util.isEmpty(_key)) {
                _start = obj.start;
                _stop = obj.stop;
                var sa:GetTextAction = new GetTextAction();
                sa.key = _key;
                ActionStarter.instance.execute(sa, new Callback(this, getTextActionHandler));
            }
        }
    }


    private function getTextActionHandler(sa:GetTextAction):void {
        var text:String = "";
        if (sa.data is Array) {
            var arr:Array = sa.data as Array;
            if (arr.length == 1) {
                var tb:TextBaustein = arr[0]
                replaceFieldText(tb.text);
            }
            if (arr.length > 1) {
               var cmd:SelectBoCommand = new SelectBoCommand("Textbaustein auswählen","search");
               cmd.callBack = new Callback(this, selectionHandler) ;
               cmd.dataTable=arr;
               cmd.start(invoker);
            }
        }
    }

    private function selectionHandler(boref:BoReference):void {
    	if(boref==null) return;
        boref.getBusinessObject(new Callback(null, function(tb:TextBaustein):void {
             if(tb!=null){
                 replaceFieldText(tb.text);
             }
        }));
    }

    private function replaceFieldText(text:String):void {
        _editField.replaceText(_start,_stop,_key,text);
    }

}
}