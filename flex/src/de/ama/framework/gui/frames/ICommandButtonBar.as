package de.ama.framework.gui.frames {
import de.ama.framework.command.Command;
import de.ama.framework.util.Callback;

import mx.collections.ArrayCollection;

public interface ICommandButtonBar {
    function addCommand(command:Command):void;
    function getCommands():ArrayCollection;

    function set callBack(callBack:Callback):void;

}
}