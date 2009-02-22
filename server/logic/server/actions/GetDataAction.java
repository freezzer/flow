package server.actions;


import server.services.Environment;


public class GetDataAction extends ServerAction {

    public void execute() {
        Object model = getData();

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
        if(model!=null) {
            String s = Environment.getXmlService().toXmlString(model);
            System.out.println("received: \n" +s);
        }

    }

    public boolean needsUser() {
        return false;
    }
}