package de.ama.framework.action {
import de.ama.framework.util.Callback;
import de.ama.framework.util.Environment;
import de.ama.framework.util.Util;

import hessian.client.HessianService;

import mx.rpc.AsyncToken;
import mx.rpc.IResponder;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.rpc.remoting.mxml.RemoteObject;

public class ActionStarter implements IResponder{

    private static var _instance:ActionStarter = null;

    private  var blazedsStub:RemoteObject = null;
    private  var hessianStub:HessianService = null;

    private  var startedAction:Object = new Object();
    private  var deferredAction:Object = null;


    public static function get instance():ActionStarter {
        if (_instance == null) {
            _instance = new ActionStarter();
        }
        return _instance;
    }

    public function ActionStarter() {

        if (Environment.useHessianProtocoll()) {
            hessianStub = new HessianService(Environment.getServerUrl() + "/action");
//            hessianStub.showBusyCursor = true;
//            hessianStub.concurrency = "single";
        } else {
            blazedsStub = new RemoteObject();
            blazedsStub.endpoint = Environment.getServerUrl() + "/messagebroker/amf";
            blazedsStub.destination = "ActionService";
            blazedsStub.addEventListener("result", actionResultHandler);
            blazedsStub.addEventListener("fault",  actionFaultHandler);
            blazedsStub.showBusyCursor = true;
            blazedsStub.concurrency = "single";
        }
    }


    public function execute(action:ActionScriptAction, invoker:Object = null ):void {
        this.startedAction.action = action;
        this.startedAction.invoker = invoker;

        if(action.needsLogin && !Environment.isLoggedIn()){
        	Environment.login();
        	return;
        }
        
        action.message = null;
        action.detailErrorMessage = null;
        action.userSessionId = Environment.getUserSessionId();
        action.catalog = Environment.catalog;

        if (hessianStub != null) {
            var token:AsyncToken = hessianStub.execute.send(action);
            token.addResponder(this);
        } else {
            blazedsStub.execute(action);
        }
    }


    public function actionResultHandler(event:ResultEvent):void {
        var action:ActionScriptAction = event.result as ActionScriptAction;

        if(action.needsLogin && Util.isEqual(action.userSessionId,"dead")){
            Environment.eraseLoginData();
            Environment.login();
            return;
        }

        if (!Util.isEmpty(action.detailErrorMessage) ) {
            Util.showError(Util.saveToString(action.message,"Fehler in Action "+action), action.detailErrorMessage);
        }

        if (!Util.isEmpty(action.message) && Util.isEmpty(action.detailErrorMessage) ) {
            Util.showMessage(action.message);
        }

        if(startedAction.invoker is Callback){
            Callback(startedAction.invoker).execute(action);
        }
    }

    public function actionFaultHandler(event:FaultEvent):void {
       Util.showError(event.fault.faultString, event.fault.faultDetail)
    }


    public function get invoker():Object {
        return startedAction.invoker;
    }


    public function result(data:Object):void {
        actionResultHandler(ResultEvent(data))
    }

    public function fault(info:Object):void {
        if (info is FaultEvent ) {
        	if(Util.isEqual("Couldn't establish a connection to ''",FaultEvent(info).fault.faultDetail)){
        		return ;
        	}
            actionFaultHandler(FaultEvent(info))
        }
    }
}
}