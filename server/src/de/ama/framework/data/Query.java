

package de.ama.framework.data;

import de.ama.util.Util;

import java.io.Serializable;

/**
 * User: Andreas Marochow
 * Date: 27.11.2004
 */
public class Query implements Serializable {

    private String targetName;
    private String orderColumn = "";
    private long limit = -1;
    private boolean negated = false;
    private Condition condition;
           
    public Query() {
        // for Serializable
    }

    public Query(Class target) {
       this(target, null, -1, null);
    }

    public Query(Class target, Condition condition) {
       this(target, null, -1, condition);
    }

    public Query(Class target, Condition condition, String orderColumn ) {
       this(target, orderColumn, -1, condition);
    }

    public Query(Class target, Condition condition, long limit ) {
       this(target, null, limit, condition);
    }

    public Query(Class target, String orderColumn, long limit, Condition condition) {
        this.targetName = target.getName();
        this.orderColumn = orderColumn;
        this.limit = limit;
        this.condition = condition;
    }

    public Class getTarget() {
        return Util.createClass(targetName.replace("::","."));
    }

    public void setTarget(Class target) {
        this.targetName = target.getName();
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public Condition getCondition() {
        return condition;
    }

    public boolean isNegated() {
        return negated;
    }

    public void setNegated(boolean negated) {
        this.negated = negated;
    }
}