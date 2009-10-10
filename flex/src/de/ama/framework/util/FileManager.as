package de.ama.framework.util {
import flash.events.Event;
import flash.events.IOErrorEvent;
import flash.net.FileFilter;
import flash.net.FileReference;
import flash.net.URLRequest;
import flash.net.URLVariables;
import flash.net.navigateToURL;

public class FileManager {

    ///////////////////////////////// navigate to File ////////////////////////////////////////

    public function showFile(path:String):void {
        var request:URLRequest = new URLRequest(Environment.getServerUrl() + "/download");
        var variables:URLVariables = new URLVariables();
        variables.path = path;
        request.method = "POST";
        request.data = variables;


        try {
            navigateToURL(request);
        } catch (e:Error) {
            Util.showError("Error while file upload",e.message);
        }
    }

    ///////////////////////////////// upload File ////////////////////////////////////////


    public  var serverPath:String;
    public  var description:String;
    private var fileRef:FileReference = new FileReference();
    private var callback:Callback;

    //////////////////////////// via Servlet ///////////////////////////////////////////////

    public function uploadFileViaServlet(description:String, callback:Callback=null):void {
        fileRef.addEventListener(Event.SELECT, sendViaServlet);
        fileRef.addEventListener(Event.COMPLETE, uploadViaServletloadComplete);
        this.callback = callback;
        this.description = description;

        fileRef.browse([new FileFilter("Images (*.jpg, *.jpeg, *.png, *.gif)", 
				"*.jpg; *.jpeg; *.png; *.gif;")]);
    }

    private function sendViaServlet(e:Event):void {
        try {
            var request:URLRequest = new URLRequest(Environment.getServerUrl() + "/upload");
            var variables:URLVariables = new URLVariables();
            variables.onlyImage   = "true";
            variables.description = this.description;
            variables.catalog    = Environment.catalog;
            variables.userSessionId     = Environment.getUserSessionId();

            request.method = "POST";
            request.data = variables;
            fileRef.upload(request);
        } catch (err:Error) {
            Util.showError("File upload failed");
        }
    }

    private function uploadViaServletloadComplete(e:Event):void {
		if(callback!=null){
            callback.execute(this);
        }
    }

    //////////////////////////// via Action ///////////////////////////////////////////////

//    public function uploadFile(serverPath:String, callback:Callback=null):void {
//        fileRef.addEventListener(Event.SELECT, fileRef_select);
//        fileRef.addEventListener(Event.COMPLETE, fileRef_loadComplete);
//        this.callback = callback;
//        this.serverPath = serverPath;
//        fileRef.browse();
//    }

//    private function fileRef_select(e:Event):void {
//        fileRef.load();
//    }

//    private function fileRef_loadComplete(e:Event):void {
//        var fa:FileAction = new FileAction();
//
//        fa.fileName = serverPath;
//
//        if(serverPath.lastIndexOf("/")== serverPath.length-1) {
//            serverPath += fileRef.name;
//        }
//        fa.fileName = serverPath;
//        fa.data = fileRef.data;
//        ActionStarter.instance.execute(fa,new Callback(this, actionReturned)) ;
//    }

//    private function actionReturned(a:ActionScriptAction):void {
//        if(callback!=null){
//            callback.execute(this);
//        }
//    }


    //    private function fileRef_select(e:Event):void {
    //        try {
    //            var fileRef:FileReference = FileReference(e.target);
    //            var request:URLRequest = new URLRequest(Environment.getServerUrl() + "/upload");
    //            var variables:URLVariables = new URLVariables();
    //            variables.fileName = fileRef.name;
    //            variables.serverPath = this.serverPath;
    //
    //            request.method = "POST";
    //            request.data = variables;
    //            fileRef.upload(request);
    //        } catch (err:Error) {
    //            Util.showError("File upload failed");
    //        }
    //
    //    }
    //


    ///////////////////////////////// save data to File ////////////////////////////////////////

//    public function saveFile(filedata:Object, fname:String):void {
//        var fr:FileReference = new FileReference();
//        fr.addEventListener(Event.COMPLETE, onFileSave);
//        fr.addEventListener(Event.CANCEL, onCancel);
//        fr.addEventListener(IOErrorEvent.IO_ERROR, onSaveError);
//        fr.save(filedata, fname);
//    }

    private function onFileSave(e:Event):void {
    }

    private function onCancel(e:Event):void {
    }

    private function onSaveError(e:IOErrorEvent):void {
        Util.showError("Error Saving File : " + e.text);
    }


}
}