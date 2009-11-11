
       package de.ama.framework.command {
       import de.ama.framework.gui.frames.ListPanel;
       import de.ama.framework.gui.frames.TreeEditor;
       import de.ama.framework.gui.frames.TreeNode;

       public class CopyNodeCommand  extends Command {

           public function CopyNodeCommand(label:String="kopieren",icon:String="copy") {
               super(label,icon);
           }

           override protected function execute():void {
               if(invoker is TreeEditor){
                   var treeEditor:TreeEditor = TreeEditor(invoker);
                   treeEditor.copySelectedNode();
               }
               if(invoker is ListPanel){
                   var lp:ListPanel = ListPanel(invoker);
                   lp.copySelectedRow();
               }

           }

       }}
