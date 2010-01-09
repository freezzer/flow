package de.ama.services.text {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.command.*;
import de.ama.framework.gui.fields.EditField;
import de.ama.framework.util.Callback;

public class SelectTextBausteinCommand extends Command{
    private var _key:String;
    private var _start:int;
    private var _stop:int;

    public function SelectTextBausteinCommand(label:String = "Textbaustein auswaehlen", icon:String = "search") {
        super(label, icon);
    }

    override protected function execute():void {
        var ef:EditField = EditField(invoker);
        var obj:Object = ef.getTextAtCaret();

        if (obj != null) {
            _key = obj.key;
            _start = obj.start;
            _stop = obj.start + obj.key.length;

            var sa:GetTextAction = new GetTextAction();
            sa.key = _key;
            ActionStarter.instance.execute(sa, new Callback(this, getTextActionHandler));
        }
    }


    private function getTextActionHandler(sa:GetTextAction):void {
        var text:String = "";
        if (sa.data is Array) {
            var arr:Array = sa.data as Array;
            if (arr.length == 1) {
                var tb:TextBaustein = arr[0]
                replaceFieldText(tb);
            }
            if (arr.length > 1) {
               var cmd:SelectBoCommand = new SelectBoCommand("Textbaustein auswählen","search");
               cmd.callBack = new Callback(this, replaceFieldText) 
               cmd.dataTable=arr;
               cmd.start(invoker);
            }
        }
    }

    private function replaceFieldText(tb:TextBaustein):void {
        if(tb==null) return;
        var ef:EditField = EditField(invoker);
        var fieldText:String = ef.getInputText();
        if (_start > 0) _start++;
        var pre:String = fieldText.substr(0, _start);
        var post:String = fieldText.substr(_stop);
        ef.setInputText(pre + tb.text + post);
        ef.setCaretPosition(_stop);
    }

}
}