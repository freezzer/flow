package de.ama.framework.gui.frames {
import de.ama.framework.data.Data;
import de.ama.framework.data.DataTable;
import de.ama.framework.util.Util;

import mx.collections.ArrayCollection;

public class TreeNode {

    private var _data:Data;
    private var _dataTable:DataTable;

    public var path:String;
    public var children:ArrayCollection = new ArrayCollection();
    public var prototypes:ArrayCollection = new ArrayCollection();
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
        this.prototypes = prototypes;
    }

    public function addChild(child:TreeNode):void {
        children.addItem(child);
    }

    public function addPrototype(child:TreeNode):void {
        prototypes.addItem(child);
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

        for each(var proto:TreeNode in prototypes) {
            var dataValue:Object = _data.getValue(proto.path);
            if (dataValue is DataTable ) {
            	if(proto.isListView){
            		var node:TreeNode = proto.createNode(_data);
            		node._dataTable = DataTable(dataValue);
                    children.addItem(node);
            	} else {
	                for each(var d:Data in DataTable(dataValue).collection) {
	                    children.addItem(proto.createNode(d));
	                }
	            }
            } else if (dataValue is Data) {
                children.addItem(proto.createNode(Data(dataValue)));
            }
        }
    }

    private function createNode(data:Data):TreeNode {
        var node:TreeNode = clone();
        node.setData(data);
        return node;
    }

    public function clone():TreeNode {
       var ret:TreeNode = new TreeNode(this.path,
                this.labelPrefix,
                this.labelPath,
                this.isListView,
                this.iconName);
        ret.prototypes = this.prototypes;
        return ret;
                
    }


    public function toString():String {
        if(_data!=null){
            return _data.getName();
        }
        return "TreeNode";
    }
}
}