package server.actions;


import de.ama.framework.action.ActionScriptAction;


public class GetDataAction extends ActionScriptAction {

    public void execute() {

//        if(model.equals("a")){
//            Workmodel wm = new Workmodel();
//            wm.setZollstelle_ausfuhr("DE00123");
//            wm.setZollstelle_ausgang("DE00124");
//            Position pos = new Position();
//            pos.addItemToPackstuecke(new Packstueck());
//            wm.addItemToPositionen(pos);
//            model = wm;
//        }
//        if(model.equals("b")){
//            Kopf kopf = new Kopf();
//            kopf.setAngemeldet(new Date());
//            kopf.setErstellt(new Date());
//            kopf.setMrn("MNDE133145654");
//            model = kopf;
//        }
//

    }

    public boolean needsUser() {
        return false;
    }
}