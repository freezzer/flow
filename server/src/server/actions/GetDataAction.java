package server.actions;

import gen.app.ausfuhr.Workmodel;
import gen.app.ausfuhr.Position;
import gen.app.ausfuhr.Packstueck;
import gen.app.ausfuhr.Kopf;

import java.util.Date;

import server.services.Environment;


public class GetDataAction extends ServerAction {

    public void execute() {
        String tmp = (String) getData();
        Object model =null;
        if(tmp.equals("a")){
            Workmodel wm = new Workmodel();
            wm.setZollstelle_ausfuhr("DE00123");
            wm.setZollstelle_ausgang("DE00124");
            Position pos = new Position();
            pos.addItemToPackstuecke(new Packstueck());
            wm.addItemToPositionen(pos);
            model = wm;
        } else {
            Kopf kopf = new Kopf();
            kopf.setAngemeldet(new Date());
            kopf.setErstellt(new Date());
            kopf.setMrn("MNDE133145654");
            model = kopf;
        }

        if(model!=null) {
            String s = Environment.getXmlService().toXmlString(model);
            setData(s);
        }

    }

    public boolean needsUser() {
        return false;
    }
}