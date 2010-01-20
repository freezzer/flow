package praxis.view.kalender {     
import de.ama.framework.command.*;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.gui.frames.*;
import de.ama.services.Factory;

import mx.controls.DateChooser;
import mx.controls.LinkButton;
public class CalendarEditor extends PanelEditor {
     private var _dateChoser:DateChooser;
     private var _dayButton:LinkButton;
     private var _weekButton:LinkButton;
     private var _monthButton:LinkButton;
     
     override public function createData():BusinessObject {
       return Factory.createBean("CalendarEntry"); 
     } 
     
     override public function addCommands():void {
        label = "Kalender"
        permissionContext="Kalender";     

        var cmd:Command;
        cmd = new SaveBoCommand("Kalender speichern","save");
//        cmd.permissionId = "Kalender:SaveBoCommand (Kalender speichern)";
        addCommand(cmd);
     } 

    
     override public function addPanels():void {
        _dayButton = LinkButton(addChild(new LinkButton()));
        _dayButton.x = 18; 
        _dayButton.y = 10; 
        _dayButton.label = "Tag"; 

        _weekButton = LinkButton(addChild(new LinkButton()));
        _weekButton.x = 71; 
        _weekButton.y = 10; 
        _weekButton.height = 50; 
        _weekButton.label = "Woche"; 

        _monthButton = LinkButton(addChild(new LinkButton()));
        _monthButton.x = 135; 
        _monthButton.y = 10; 
        _monthButton.label = "Monat"; 

        _dateChoser = DateChooser(addChild(new DateChooser()));
        _dateChoser.x = 10;
        _dateChoser.y = 40;
        _dateChoser.width = 185;
        _dateChoser.dayNames = [ "S", "M", "D", "M", "D", "F", "S" ];
        _dateChoser.monthNames = [ "Januar", "Februar", "März", "April", "Mai", "Juni", "July","August","September","Oktober","November","Dezember" ];
     } 

   }
}

