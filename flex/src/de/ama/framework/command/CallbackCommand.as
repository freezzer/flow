
       package de.ama.framework.command {
       import de.ama.framework.util.Callback;

       public class CallbackCommand  extends Command {

           private var _callback:Callback;

           public function CallbackCommand(label:String, icon:String, callback:Callback, permissionId:String=null) {
               super(label,icon);
               this._callback = callback;
               this.permissionId = permissionId;
           }

           override protected function execute():void {
               _callback.execute(invoker);
           }

       }}
