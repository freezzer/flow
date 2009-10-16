package de.ama.framework.gui.frames {
import de.ama.framework.gui.frames.TabbedApplicationPanel;

public interface FlowApplication {

     function goTo(dest:String):void;

     function getContentPane():ApplicationPanel;

}
}