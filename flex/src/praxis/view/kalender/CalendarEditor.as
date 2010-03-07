package praxis.view.kalender {
import de.ama.framework.action.ActionStarter;
import de.ama.framework.command.*;
import de.ama.framework.data.BoReference;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.QueryDataProvider;
import de.ama.framework.gui.frames.*;
import de.ama.framework.util.Callback;
import de.ama.services.Factory;

import mx.containers.HBox;
import mx.controls.DateChooser;
import mx.controls.LinkButton;

import praxis.bom.CalendarEntry;
import praxis.bom.Resource;

public class CalendarEditor extends PanelEditor {
     private var _dateChoser:DateChooser;
     private var _dayButton:LinkButton;
     private var _weekButton:LinkButton;
     private var _monthButton:LinkButton;
     private var _timeLines:HBox;
     
     private var _startTime:Date;
     private var _endTime:Date;
     private var _deltaTime:int; // in minutes

	public function CalendarEditor(){
		_startTime = new Date();
		_startTime.setHours(8,0,0,0);
		_endTime = new Date();
		_endTime.setHours(18,0,0,0);
		_deltaTime = 15;
		addTimeLine(new TimeLine(null,_startTime,_endTime,_deltaTime));
	}

     override public function createData():BusinessObject {
       return Factory.createBean("CalendarEntry"); 
     } 
     
     override public function addCommands():void {
        label = "Kalender"
        permissionContext="Kalender";     

        var cmd:Command;
        cmd = new SaveCalendarCommand("Kalender speichern","save");
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
        _weekButton.label = "Woche"; 

        _monthButton = LinkButton(addChild(new LinkButton()));
        _monthButton.x = 135; 
        _monthButton.y = 10; 
        _monthButton.label = "Monat"; 

        _dateChoser = DateChooser(addChild(new DateChooser()));
        _dateChoser.x = 10;
        _dateChoser.y = 40;
        _dateChoser.width = 185;
        _dateChoser.dayNames = [ "Sa", "Mo", "Di", "Mi", "Do", "Fr", "So" ];
        _dateChoser.monthNames = [ "Januar", "Februar", "März", "April", "Mai", "Juni", "July","August","September","Oktober","November","Dezember" ];

        _timeLines = HBox(addChild(new HBox()));
        _timeLines.setStyle("left",210);
        _timeLines.setStyle("top",10);
        _timeLines.setStyle("right",10);
        _timeLines.setStyle("bottom",10);
        _timeLines.setStyle("borderStyle","solid");
        _timeLines.setStyle("horizontalGap",1);

      
        load();  
     }

     public function load():void{
         var dp:QueryDataProvider = new QueryDataProvider(new Resource());
         dp.getTable( new Callback(null,function(data:Array):void{
				for each(var r:Resource in data){
					addTimeLine(new TimeLine(r,_startTime,_endTime,_deltaTime));
				}
         }));
         
        var sa:CalendarAction    = new CalendarAction();
        sa.type = CalendarAction.LOAD_DAY;
        ActionStarter.instance.execute(sa , new Callback(this, resulthandler ));
     }

     public function resulthandler(sa:CalendarAction):void{
    	setCalendarEntries(sa.data as Array);
     }

     public function addTimeLine(timeLine:TimeLine):void{
     	_timeLines.addChild(timeLine);
     }

     public function getTimeLines():Array {
       var ret:Array = new Array();
       var children:Array = _timeLines.getChildren();
       for each (var o:Object in children){
           if(o is TimeLine && TimeLine(o).resource!=null){
           	  ret.push(o);
           }
       }
       return ret;
     }
     
     public function getTimeLine(r:BoReference):TimeLine {
       var lines:Array = getTimeLines();
       for each (var l:TimeLine in lines){
              if(l.resource.oid == r.oid){
                 return l;
              }
       }
       return null;
    }

     public function getCalendarEntries():Array {
       var ret:Array = new Array();
       var lines:Array = getTimeLines();
       for each (var l:TimeLine in lines){
          ret = ret.concat( l.getCalendarEntries() );
       }
       return ret;
    }


    public function clearAllCalendarEntries():void {
       var lines:Array = getTimeLines();
       for each (var l:TimeLine in lines){
          l.removeAllItemPanels();
       }
    }
    
    public function setCalendarEntries(arr:Array):void {
       clearAllCalendarEntries();
       for each (var e:CalendarEntry in arr){
           var timeLine:TimeLine = getTimeLine(e.resource);
           if(timeLine)
              timeLine.insertItemPanel(new ItemPanel(e));
       }
    }
    
}}


