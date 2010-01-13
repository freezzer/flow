       /* 
       null
       */ 

       package de.ama.framework.command {
       import de.ama.framework.gui.frames.ListPanel;
       import de.ama.framework.gui.frames.TreeEditor;

       public class CreateNodeCommand  extends Command {
           private var type:String;

           public function CreateNodeCommand(label:String="anlegen",icon:String="new") {
               super(label,icon);
           }

           override protected function execute():void {
               if(invoker is TreeEditor){
                   var treeEditor:TreeEditor = TreeEditor(invoker);
                   treeEditor.addNewNode();
               }
               if(invoker is ListPanel){
                   var lp:ListPanel = ListPanel(invoker);
                   lp.addNewRow();
               }
           }

       }}
