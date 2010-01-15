

package de.ama.framework.data;

import de.ama.util.Composite;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * User: Andreas Marochow
 * Date: 27.11.2004
 */
public class Condition implements Serializable{
    public static final String EQ = " = ";
    public static final String LE = " <= ";
    public static final String GE = " >= ";
    public static final String LT = " < ";
    public static final String GT = " > ";
    public static final String LIKE = " like ";
    public static final String AND = " AND ";
    public static final String OR = " OR ";

    private String path;
    private String op;
    private Object value;
    private String concatOperator = "";
    private boolean negated;
    private List children = null;

    public Condition() {
    }

    public Condition(String path, String op, Object value) {
        this.path = path;
        this.op = op;
        this.value = value;
    }

    public Condition and(Condition c) {
        concatOperator=AND;
        addChild(c);
        return this;
    }

    private void addChild(Condition c) {
        if(children==null){
            children = new ArrayList();
        }
        children.add(c);
    }

    public Condition or(Condition c) {
        concatOperator=OR;
        addChild(c);
        return this;
    }


    public String getPath()                     {           return path;            }
    public void setPath(String path)            {           this.path = path;       }

    public String getOp()                       {           return op;              }
    public void setOp(String op)                {           this.op = op;           }

    public Object getValue()                    {           return value;           }
    public void setValue(Object value)          {           this.value = value;     }

    public String getConcatOperator()           {           return concatOperator;  }
    public void setConcatOperator(String concatOperator) {  this.concatOperator = concatOperator;     }

    public boolean isNegated()                  {           return negated;         }
    public void setNegated(boolean negated)     {           this.negated = negated; }

    public List getChildren()                   {           return children;        }
    public void setChildren(List children)      {           this.children = children;  }
    
}