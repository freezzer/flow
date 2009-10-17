package de.ama.framework.gui.frames {
import de.ama.framework.command.Command;
import de.ama.framework.command.Invoker;
import de.ama.framework.data.Data;
import de.ama.framework.data.Data;
import de.ama.framework.data.DataTable;
import de.ama.framework.util.Util;

import mx.collections.ArrayCollection;

public class TreeNode implements Invoker{

    private var _parent:TreeNode;
    private var _data:Data;
    private var _dataTable:DataTable;

    public var children:ArrayCollection = new ArrayCollection();
    public var templates:ArrayCollection = new ArrayCollection();
    public var commands:ArrayCollection = new ArrayCollection();

    public var type:String;
    public var path:String;
    public var labelPrefix:String;
    public var labelPath:String;
    public var isListView:Boolean = false;
    public var iconName:String = "folder";


    public function TreeNode(path:String, labelPrefix:String, labelPath:String, isListView:Boolean, iconName:String) {
        this.path = path;
        this.labelPath = labelPath;
        this.labelPrefix = labelPrefix;
        this.isListView = isListView;
        this.iconName = iconName;
        this.templates = templates;
    }

    public function addCommand(command:Command):void {
        commands.addItem(command);
    }

    public function removeChild(child:TreeNode):void {
        var i:int = children.getItemIndex(child);
        if(i>=0){
          children.removeItemAt(i);
          child.parent = null;
          _dataTable.removeItem(child._data);  
        }
    }

    public function addChild(child:TreeNode, intoDataTable:Boolean):void {
        child.parent = this;
        if(intoDataTable){
           dataTable.addItem(child.data);
        }
        children.addItem(child);
    }

    public function addNewChild():void {
        var data:Data = dataTable.addNewItem();
        var template:TreeNode = findTemplate(data);
        addChild(template.templateClone(data),false);
    }

    public function clone():TreeNode {
        return templateClone(_data.clone());
    }

    public function addTemplate(child:TreeNode):void {
        child.parent = this;
        templates.addItem(child);
    }

    public function get label():String {
        if (!Util.isEmpty(labelPath)) {
            return Util.saveToString(labelPrefix) + _data.getProperty(labelPath);
        }
        return Util.saveToString(labelPrefix,"node");

    }

    public function getData():Data {
        return data;
    }

    public function setData(data:Data):void {
        if(_data!=null){
            throw new Error("this TreeNode is not a prototype, it is in use !");
        }
        _data = data;
        build();
    }

    private function build():void {

        for each(var proto:TreeNode in templates) {
            var dataValue:Object = _data.getValue(proto.path);
            if (dataValue is DataTable ) {
            	if(proto.isListView){
            		var node:TreeNode = proto.templateClone(_data);
            		node._dataTable = DataTable(dataValue);
                    addChild(node,false);
            	} else {
	                for each(var d:Data in DataTable(dataValue)) {
	                    addChild(proto.templateClone(d),false);
	                }
	            }
            } else if (dataValue is Data) {
                addChild(proto.templateClone(Data(dataValue)),false);
            }
        }
    }

    public function templateClone(data:Data):TreeNode {
        var node:TreeNode = new TreeNode(this.path,
                 this.labelPrefix,
                 this.labelPath,
                 this.isListView,
                 this.iconName);
         node.commands = this.commands;
         node.templates = this.templates;

        node.setData(data);
        return node;
    }

    public function findTemplate(data:Data):TreeNode {
        if(templates.length==1){
            return TreeNode(templates.getItemAt(0));
        }
        
        for each(var proto:TreeNode in templates) {
            if(Util.isEqual(proto.type,data.getName())){
                return proto;
            }
        }
        return null;
    }

    public function toString():String {
        if(dataTable!=null){
            return dataTable.asString("");
        } else if(data!=null){
            return data.asString("");
        }
        return "TreeNode has no Data";
    }

    public function set parent(parent:TreeNode):void {
        _parent = parent;
    }


    public function get parent():TreeNode {
        return _parent;
    }

    public function get data():Data {
        return _data;
    }

    public function get dataTable():DataTable {
        return _dataTable;
    }


}
}