/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package comp.view.organisation {
import comp.bom.Person;

import de.ama.framework.data.*;
import de.ama.framework.command.Invoker;
import de.ama.framework.util.Callback;
public class OrgaPersonenProvider  implements DataProvider {

    private var _invoker:Invoker;
    private var _cb:Callback;

    public function setInvoker(value:Invoker):void {
         _invoker = value;
    }
    
    public function getTable(cb:Callback):void {
        _cb = cb;
        var array:Array = new Array();
        array.push(new Person());
        array.push(new Person());
        _cb.execute(array);
    } 
    
}}
