package de.ama.pages;

import de.ama.db.Query;
import de.ama.services.Environment;
import de.ama.services.user.User;
import org.apache.click.Page;
import org.apache.click.control.ActionLink;


public class BorderPage extends Page {
    public String stats ="1";

    public BorderPage() {
        addControl(new ActionLink("homeLink",this, "onHomeClick"));
        addControl(new ActionLink("impressumLink",this, "onImpresumClick"));
    }


    @Override
    public void onGet() {
        Environment.getPersistentService().join("flow");
        stats = "u("+Environment.getPersistentService().getObjectCount(new Query(User.class))+")";
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
