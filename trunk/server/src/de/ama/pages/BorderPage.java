package de.ama.pages;

import org.apache.click.Page;
import org.apache.click.control.ActionLink;
import de.ama.services.Environment;
import de.ama.services.impl.User;
import de.ama.framework.data.QueryDescription;
import de.ama.db.Query;
import de.ama.huyua.Picture;


public class BorderPage extends Page {
    public String stats ="1";

    public BorderPage() {
        addControl(new ActionLink("homeLink",this, "onHomeClick"));
        addControl(new ActionLink("impressumLink",this, "onImpresumClick"));
    }


    @Override
    public void onGet() {
        Environment.getPersistentService().join("huyua_production");
        stats = "u("+Environment.getPersistentService().getObjectCount(new Query(User.class))+")";
        stats += " p("+Environment.getPersistentService().getObjectCount(new Query(Picture.class))+")";
        Environment.getPersistentService().leave();
        super.onGet();
    }

    public boolean onLogoutClick() {
        if (getContext().hasSession()) {
           getContext().getSession().invalidate();
        }
        setRedirect(HomePage.class);
        return false;
    }

    public boolean onImpresumClick() {
        setRedirect(ImpressumPage.class);
        return false;
    }

    public boolean onHomeClick() {
        setRedirect(HomePage.class);
        return false;
    }

    @Override
    public String getTemplate() {
       return "border.htm";
    }

}
