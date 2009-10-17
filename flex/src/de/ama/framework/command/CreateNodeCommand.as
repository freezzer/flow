       /* 
       null
       */ 

       package de.ama.framework.command {
       import de.ama.framework.gui.frames.TreeNode;

       public class CreateNodeCommand  extends Command {
           public function CreateNodeCommand(label:String) {
               super(label);
           }

           override public function execute():void {
               var invoker:Invoker = context.invoker;
               if(invoker is TreeNode){
                   var node:TreeNode = TreeNode(invoker);
                   node.createChild();
               }
           }

       }}
