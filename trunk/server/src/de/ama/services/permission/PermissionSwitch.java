package de.ama.services.permission;

import de.ama.db.PersistentMarker;

/**
 * Created by IntelliJ IDEA.
 * User: x
 * Date: 03.11.2009
 * Time: 00:56:38
 * To change this template use File | Settings | File Templates.
 */
public class PermissionSwitch implements PersistentMarker {
    public String key;
    public boolean isOn;

    public PermissionSwitch(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionSwitch)) return false;

        PermissionSwitch that = (PermissionSwitch) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (isOn ? 1 : 0);
        return result;
    }
}
