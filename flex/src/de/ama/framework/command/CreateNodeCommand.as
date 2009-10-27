       /* 
       null
       */ 

       package de.ama.framework.command {
       import de.ama.framework.gui.frames.TreeNode;

       public class CreateNodeCommand  extends Command {
           private var type:String;

           public function CreateNodeCommand(label:String="anlegen",icon:String="new") {
               super(label,icon);
           }

           override protected function execute():void {
               if(invoker is TreeNode){
                   var node:TreeNode = TreeNode(invoker);
                   if(node.isListView){
                      node.addNewChild();
                   } else {
                      node.parent.addNewChild(); 
                   }
               }
           }

       }}
