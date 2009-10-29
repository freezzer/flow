
       package de.ama.framework.command {
       import de.ama.framework.gui.frames.ListPanel;
       import de.ama.framework.gui.frames.TreeNode;

       public class DeleteNodeCommand  extends Command {


           public function DeleteNodeCommand(label:String="loeschen",icon:String="delete") {
               super(label,icon);
           }

           override protected function execute():void {
               if(invoker is TreeNode){
                   var parent:TreeNode = TreeNode(invoker).parent;
                   parent.removeChild(TreeNode(invoker));
               }
               if(invoker is ListPanel){
                   var lp:ListPanel = ListPanel(invoker);
                   lp.removeSelectedRow();
               }

           }
       }}
