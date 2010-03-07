// @Generated(generated/java/praxis/view/Bootstrap.java)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view;

import de.ama.services.Environment;
import de.ama.services.PermissionService;
import de.ama.services.EventService;

public class Bootstrap { 

//  @Generated("registerBeans")
    protected void registerBeans(){

         Environment.registerBean("Person", praxis.bom.Person.class);
         Environment.registerBean("Adresse", praxis.bom.Adresse.class);
         Environment.registerBean("Patient", praxis.bom.Patient.class);
         Environment.registerBean("Eintrag", praxis.bom.Eintrag.class);
         Environment.registerBean("Dokument", praxis.bom.Dokument.class);
         Environment.registerBean("CodeElement", praxis.bom.CodeElement.class);
         Environment.registerBean("Resource", praxis.bom.Resource.class);
         Environment.registerBean("CalendarEntry", praxis.bom.CalendarEntry.class);
    }

//  @Generated("registerPermissions")
    protected void registerPermissions(){

         Environment.getPermissionService().registerPermissionContext("Person", praxis.permission.PermissionPerson.class);
         Environment.getPermissionService().registerPermissionContext("Patient", praxis.permission.PermissionPatient.class);
         Environment.getPermissionService().registerPermissionContext("Stammdaten", praxis.permission.PermissionStammdaten.class);
         Environment.getPermissionService().registerPermissionContext("Kalender", praxis.permission.PermissionKalender.class);
         Environment.getPermissionService().registerPermissionContext("HauptMenu", praxis.permission.PermissionHauptMenu.class);
    }

//  @Generated("registerEventConsumer")
    protected void registerEventConsumer(){

    }

//  @Generated("preMain")
    public void preMain() {
         registerBeans();
         registerPermissions();
         registerEventConsumer();
         System.out.println("Bootstrap done OK");
    }


// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}
