       /* 
       null
       */ 

       package de.ama.framework.command {
       import de.ama.framework.gui.frames.TreeNode;

       public class CreateNodeCommand  extends Command {
           
           public function CreateNodeCommand(label:String="anlegen",icon:String="new") {
               super(label,icon);
           }

           override protected function execute():void {
               var invoker:Invoker = context.invoker;
               if(invoker is TreeNode){
                   var node:TreeNode = TreeNode(invoker);
                   node.addNewChild();
               }
           }

       }}
