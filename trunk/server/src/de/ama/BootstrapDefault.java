/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/

package de.ama;

import de.ama.services.Environment;
import de.ama.services.PermissionService;
import de.ama.services.EventService;
import de.ama.services.text.TextBaustein;
import de.ama.services.text.PermissionTextBausteine;

public class BootstrapDefault {

    protected void registerBeans(){

         Environment.registerBean("TextBaustein", TextBaustein.class);
    }

    protected void registerPermissions(){

         Environment.getPermissionService().registerPermissionContext("TextBausteine", PermissionTextBausteine.class);
    }

    protected void registerEventConsumer(){

    }

    public void preMain() {
         registerBeans();
         registerPermissions();
         registerEventConsumer();
         System.out.println("Default Bootstrap done OK");
    }

}