

package de.ama.framework.data;

import java.io.Serializable;

/**
 * User: Andreas Marochow
 * Date: 27.11.2004
 */
public class Query implements Serializable {

    private Class target;
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
        this.target = target;
        this.orderColumn = orderColumn;
        this.limit = limit;
        this.condition = condition;
    }

    public Class getTarget() {
        return target;
    }

    public void setTarget(Class target) {
        this.target = target;
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