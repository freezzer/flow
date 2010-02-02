package de.ama.framework.action {
import de.ama.framework.util.Callback;
import de.ama.framework.util.Util;
import de.ama.services.Environment;

//import hessian.client.HessianService;

import mx.collections.ArrayList;
//import mx.rpc.AsyncToken;
import mx.rpc.IResponder;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.rpc.remoting.mxml.RemoteObject;

public class ActionStarter implements IResponder{

    private static var _instance:ActionStarter = null;

    private  var blazedsStub:RemoteObject = null;
//    private  var hessianStub:HessianService = null;

    private  var actionIdGenerator:int = 1;
    private  var callbacks:ArrayList = new ArrayList();


    public static function get instance():ActionStarter {
        if (_instance == null) {
            _instance = new ActionStarter();
        }
        return _instance;
    }

    public function ActionStarter() {

        if (Environment.useHessianProtocoll()) {
//            hessianStub = new HessianService(Environment.getServerUrl() + "/action");
            //            hessianStub.showBusyCursor = true;
            //            hessianStub.concurrency = "single";
        } else {
            blazedsStub = new RemoteObject();
            blazedsStub.endpoint = Environment.getServerUrl() + "/messagebroker/amf";
            blazedsStub.destination = "ActionService";
            blazedsStub.addEventListener("result", actionResultHandler);
            blazedsStub.addEventListener("fault", actionFaultHandler);
            blazedsStub.showBusyCursor = true;
            //            blazedsStub.concurrency = "single";
        }
    }


    public function execute(action:ActionScriptAction, cb:Callback = null):void {
        if (cb != null) {
            var item:Object = new Object;
            action.actionId = actionIdGenerator++;
            item.callback = cb;
            item.id = action.actionId;
            callbacks.addItem(item);
        }

        if (action.needsLogin && !Environment.isLoggedIn()) {
            Environment.login();
            return;
        }

        action.message = null;
        action.detailErrorMessage = null;
        action.userSessionId = Environment.getUserSessionId();
        action.catalog = Environment.catalog;

//        if (hessianStub != null) {
//            var token:AsyncToken = hessianStub.execute.send(action);
//            token.addResponder(this);
//        } else {

        blazedsStub.execute(new ActionTransporter(action.catalog,action));//        }
    }


    public function actionResultHandler(event:ResultEvent):void {
        var action:ActionScriptAction = event.result as ActionScriptAction;

        if (action.needsLogin && Util.isEqual(action.userSessionId, "dead")) {
            Environment.eraseLoginData();
            Environment.login();
            return;
        }

        if (!Util.isEmpty(action.detailErrorMessage)) {
            Util.showError(Util.saveToString(action.message, "Fehler in Action " + action), action.detailErrorMessage);
        }

        if (!Util.isEmpty(action.message) && Util.isEmpty(action.detailErrorMessage)) {
            Util.showMessage(action.message);
        }

        var callback:Callback = getCallbackForAction(action);
        if (callback!=null) {
            callback.execute(action);
        }
    }

    private function getCallbackForAction(sa:ActionScriptAction):Callback {
        var callback:Callback = null;
        var l:int = callbacks.length;
        var item:Object=null;
        var old:Object=null;
        for (var i:int=0; i<l; i++) {
        	item = callbacks.getItemAt(i);
            if (sa.actionId == item.id) {
                callbacks.removeItem(item);
				return item.callback;	                
            }
            if ((actionIdGenerator - item.id) > 10) {
                old = item;
            }
        }

        callbacks.removeItem(old);
        return null;
    }

    public function actionFaultHandler(event:FaultEvent):void {
        Util.showError(event.fault.faultString, event.fault.faultDetail)
    }

    public function result(data:Object):void {
        actionResultHandler(ResultEvent(data))
    }

    public function fault(info:Object):void {
        if (info is FaultEvent) {
            if (Util.isEqual("Couldn't establish a connection to ''", FaultEvent(info).fault.faultDetail)) {
                return;
            }
            actionFaultHandler(FaultEvent(info))
        }
    }
}
}