
       package de.ama.framework.command {
       import de.ama.framework.gui.frames.TreeNode;

       public class DeleteNodeCommand  extends Command {


           public function DeleteNodeCommand(label:String) {
               super(label);
           }

           override public function execute():void {
               var invoker:Invoker = context.invoker;
               if(invoker is TreeNode){
                   var parent:TreeNode = TreeNode(invoker).parent;
                   parent.removeChild(TreeNode(invoker))
               }
           }
       }}
