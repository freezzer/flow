package de.ama.framework.command {
import de.ama.framework.data.Data;

public interface Invoker {

    function setData(data:Data):void;
    function getData():Data;

}
}