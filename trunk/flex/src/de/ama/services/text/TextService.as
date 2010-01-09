package de.ama.services.text {
import de.ama.framework.util.Util;
import de.ama.services.*;
import de.ama.framework.action.ActionStarter;
import de.ama.framework.gui.fields.EditField;
import de.ama.framework.util.Callback;

public class TextService {


    private static var _instance:TextService;
    public static function instance():TextService{
        if(_instance==null){
           _instance = new TextService();
        }
		return _instance;
    }

    public function getProposal(key:String, cb:Callback):void{
          cb.execute("So geit dat")
//        var sa:GetTextProposalAction    = new GetTextProposalAction();
//        sa.key = key;
//        ActionStarter.instance.execute(sa , new Callback(this, getProposalHandler, cb ));
    }

    private function getProposalHandler(sa:GetTextProposalAction, cb:Callback ):void {
        if(!Util.isEmpty(sa.text)){
            cb.execute(sa.text);
        }
    }

}
}