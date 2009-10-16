package de.ama.framework.gui.frames {
import de.ama.framework.command.Command;
import de.ama.framework.data.Data;
import de.ama.framework.data.DataTable;
import de.ama.framework.util.Util;

import mx.collections.ArrayCollection;

public class TreeNode {

    private var _parent:TreeNode;
    private var _data:Data;
    private var _dataTable:DataTable;

    public var children:ArrayCollection = new ArrayCollection();
    public var templates:ArrayCollection = new ArrayCollection();
    public var commands:ArrayCollection = new ArrayCollection();

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

    public function removeChild(child:TreeNode):void {
        var i:int = children.getItemIndex(child);
        if(i>0){
          children.removeItemAt(i);
          child.parent = null;
        }
    }

    public function addCommand(command:Command):void {
        commands.addItem(command);
    }

    public function addChild(child:TreeNode):void {
        child.parent = this;
        children.addItem(child);
    }

    public function clone():TreeNode {
        return createNode(_data.clone());
    }

    public function addPrototype(child:TreeNode):void {
        templates.addItem(child);
    }

    public function get label():String {
        if (!Util.isEmpty(labelPath)) {
            return Util.saveToString(labelPrefix) + _data.getProperty(labelPath);
        }
        return Util.saveToString(labelPrefix,"node");

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
            		var node:TreeNode = proto.createNode(_data);
            		node._dataTable = DataTable(dataValue);
                    addChild(node);
            	} else {
	                for each(var d:Data in DataTable(dataValue).collection) {
	                    addChild(proto.createNode(d));
	                }
	            }
            } else if (dataValue is Data) {
                addChild(proto.createNode(Data(dataValue)));
            }
        }
    }

    private function createNode(data:Data):TreeNode {
        var node:TreeNode = new TreeNode(this.path,
                 this.labelPrefix,
                 this.labelPath,
                 this.isListView,
                 this.iconName);
         node.templates = this.templates;

        node.setData(data);
        return node;
    }


    public function toString():String {
        if(_data!=null){
            return _data.getName();
        }
        return "TreeNode";
    }

    public function set parent(parent:TreeNode):void {
        _parent = parent;
    }


    public function get parent():TreeNode {
        return _parent;
    }
}
}