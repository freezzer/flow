package de.ama.framework.gui.frames {
import de.ama.framework.command.Command;
import de.ama.framework.command.Invoker;
import de.ama.framework.data.BusinessObject;
import de.ama.framework.data.SelectionModel;
import de.ama.services.Factory;
import de.ama.framework.util.Util;

import mx.collections.ArrayCollection;
import mx.controls.Tree;
import mx.events.ListEvent;

public class TreeNode implements Invoker{

    private var _tree:Tree;
    
    
    private var _parent:TreeNode;
    private var _data:BusinessObject;
    private var _dataTable:Array;
    public var  _label:String;

    public var children:ArrayCollection = new ArrayCollection();
    public var templates:ArrayCollection = new ArrayCollection();
    public var commands:ArrayCollection = new ArrayCollection();

    public var type:String;
    public var path:String;
    public var panelName:String;
    public var isListView:Boolean = false;
    public var defaultOpen:Boolean = false;
    public var iconName:String = "folder";


    public function TreeNode(path:String, label:String, isListView:Boolean,
                             iconName:String, type:String=null, panelName:String=null) {
        this.path = path;
        this._label = label;
        this.isListView = isListView;
        this.iconName = iconName;
        this.templates = templates;
        this.type = type;
        this.panelName = panelName;
    }

    public function setDefaultOpen(b:Boolean):void {
        defaultOpen = b;
    }

    public function addCommand(command:Command):void {
        commands.addItem(command);
    }


    public function select():void {
        tree.selectedItem = this;
        tree.dispatchEvent(new ListEvent(ListEvent.CHANGE));
    }
    
    public function removeChild(child:TreeNode):void {
        var i:int = children.getItemIndex(child);
        if(i>=0){
          children.removeItemAt(i);
          child.parent = null;
          var coll:ArrayCollection =  new ArrayCollection(_dataTable);
          coll.removeItemAt(coll.getItemIndex(child.data));  
		  if(i>0){
            TreeNode(children.getItemAt(i-1)).select();
		  }	else {
		  	this.select();
		  }
        }
    }

    public function addChild(child:TreeNode, intoDataTable:Boolean):void {
        child.parent = this;
        children.addItem(child);
        if(intoDataTable){
          _dataTable.push(child.data);
          child.select();
        }
    }


    public function addNewChild():void {
        var data:BusinessObject = Factory.createBean(type);
        var template:TreeNode = findTemplate(data);
        addChild(template.templateClone(data),true);
    }

    public function clone():TreeNode {
        return templateClone(_data.clone());
    }

    public function addTemplate(child:TreeNode):void {
        child.parent = this;
        templates.addItem(child);
    }

    public function get label():String {
    	if(_data!=null){
	        if (Util.isEmpty(_label)) {
	            return _data.getName();
	        }
	
	        if(_label.indexOf("{")>=0 && _label.indexOf("}")>0) {
	           var pre:String = _label.split("{")[0];
	           var post:String = _label.split("}")[1];
	           var lpath:String = _label.substring(_label.indexOf("{")+1,_label.indexOf("}"));
	           var prop:String = _data.getProperty(lpath);
	           return pre+prop+post;
	        }
        }
        return _label;
    }

    public function getData():BusinessObject {
        return data;
    }

    public function setData(data:BusinessObject):void {
        if(_data!=null){
            throw new Error("this TreeNode is not a prototype, it is in use !");
        }
        _data = data;
        build();
    }

    public function getSelectionModel():SelectionModel {
        return new SelectionModel(getData());
    }


    private function build():void {

        for each(var proto:TreeNode in templates) {
            var dataValue:Object = _data.getValue(proto.path);
            if (dataValue is Array ) {
            	if(proto.isListView){
            		var node:TreeNode = proto.templateClone(_data);
            		node._dataTable = dataValue as Array;
                    addChild(node,false);
            	} else {
	                for each(var d:BusinessObject in dataValue) {
	                    addChild(proto.templateClone(d),false);
	                }
	            }
            } else if (dataValue is BusinessObject) {
                addChild(proto.templateClone(BusinessObject(dataValue)),false);
            }
        }
    }

    public function templateClone(data:BusinessObject):TreeNode {
        var node:TreeNode = new TreeNode(this.path,
                 this._label,
                 this.isListView,
                 this.iconName,
                 this.type,
                 this.panelName);
         node.commands = this.commands;
         node.templates = this.templates;
         node.defaultOpen = this.defaultOpen;

        node.setData(data);
        return node;
    }

    public function findTemplate(data:BusinessObject):TreeNode {
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
        if(_dataTable!=null){
            var ret:String = "Array: "+_dataTable.length;
            for each (var data:BusinessObject in _dataTable ){
               ret += data.asString("  ");
            }
            return ret;
        } 
        
        if(_data!=null){
            return _data.asString("");
        } 

        return "TreeNode has no Data";
    }

    public function set parent(parent:TreeNode):void {
        _parent = parent;
    }

    public function get parent():TreeNode {
        return _parent;
    }

    public function set tree(t:Tree):void {
    	root._tree = t;
    }

    public function get tree():Tree {
    	return root._tree;
    }

    public function get root():TreeNode {
        if(_parent==null){
           return this;
        }
        return parent.root;
    }

    public function get data():BusinessObject {
        return _data;
    }

    public function get dataTable():Array {
        return _dataTable;
    }

    public function openDefaultNodes():void {
        if(tree==null) return;

        if(defaultOpen){
    		tree.expandItem(this,true);
    	}

    	for each(var node:TreeNode in children){
    		node.openDefaultNodes();
    	}

    }

}
}