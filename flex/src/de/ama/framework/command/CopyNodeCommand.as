
       package de.ama.framework.command {
       import de.ama.framework.gui.frames.TreeNode;

       public class CopyNodeCommand  extends Command {

           public function CopyNodeCommand(label:String="kopieren",icon:String="copy") {
               super(label,icon);
           }

           override protected function execute():void {
               var invoker:Invoker = context.invoker;
               if(invoker is TreeNode){
                   var parent:TreeNode = TreeNode(invoker).parent;
                   parent.addChild(TreeNode(invoker).clone(),true)
               }
           }

       }}
