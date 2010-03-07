// @Generated(generated/flex/praxis/view/Bootstrap.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.view {
import de.ama.services.Factory;
public class Bootstrap {

    public function execute():void {
        import praxis.bom.Person;
        Factory.registerBean("Person", praxis.bom.Person);
        import praxis.bom.Adresse;
        Factory.registerBean("Adresse", praxis.bom.Adresse);
        import praxis.bom.Patient;
        Factory.registerBean("Patient", praxis.bom.Patient);
        import praxis.bom.Eintrag;
        Factory.registerBean("Eintrag", praxis.bom.Eintrag);
        import praxis.bom.Dokument;
        Factory.registerBean("Dokument", praxis.bom.Dokument);
        import praxis.bom.CodeElement;
        Factory.registerBean("CodeElement", praxis.bom.CodeElement);
        import praxis.bom.Resource;
        Factory.registerBean("Resource", praxis.bom.Resource);
        import praxis.bom.CalendarEntry;
        Factory.registerBean("CalendarEntry", praxis.bom.CalendarEntry);
        import praxis.view.patient.CreateDokumentCommand;
        Factory.registerCommand("CreateDokumentCommand", praxis.view.patient.CreateDokumentCommand);
        import praxis.view.person.AdressePanel;
        Factory.registerPanel("AdressePanel", praxis.view.person.AdressePanel);
        import praxis.view.person.PersonPanel;
        Factory.registerPanel("PersonPanel", praxis.view.person.PersonPanel);
        import praxis.view.patient.EintragPanel;
        Factory.registerPanel("EintragPanel", praxis.view.patient.EintragPanel);
        import praxis.view.patient.PatientPanel;
        Factory.registerPanel("PatientPanel", praxis.view.patient.PatientPanel);
        import praxis.view.patient.PatientKopfPanel;
        Factory.registerPanel("PatientKopfPanel", praxis.view.patient.PatientKopfPanel);
        import praxis.view.stammdaten.CodeElementPanel;
        Factory.registerPanel("CodeElementPanel", praxis.view.stammdaten.CodeElementPanel);
        import praxis.view.stammdaten.ResourcePanel;
        Factory.registerPanel("ResourcePanel", praxis.view.stammdaten.ResourcePanel);
        import praxis.view.stammdaten.CodeElementEditor;
        Factory.registerEditor("CodeElementEditor", praxis.view.stammdaten.CodeElementEditor);
        import praxis.view.stammdaten.ResourceEditor;
        Factory.registerEditor("ResourceEditor", praxis.view.stammdaten.ResourceEditor);
        import praxis.view.person.PersonEditor;
        Factory.registerEditor("PersonEditor", praxis.view.person.PersonEditor);
        import praxis.view.patient.PatientEditor;
        Factory.registerEditor("PatientEditor", praxis.view.patient.PatientEditor);
        import praxis.view.person.PersonLister;
        Factory.registerLister("PersonLister", praxis.view.person.PersonLister);
        import praxis.view.patient.PatientLister;
        Factory.registerLister("PatientLister", praxis.view.patient.PatientLister);
        import praxis.view.patient.DokumenteLister;
        Factory.registerLister("DokumenteLister", praxis.view.patient.DokumenteLister);
        import praxis.view.stammdaten.CodeElementLister;
        Factory.registerLister("CodeElementLister", praxis.view.stammdaten.CodeElementLister);
        import praxis.view.stammdaten.ResourceLister;
        Factory.registerLister("ResourceLister", praxis.view.stammdaten.ResourceLister);
        import praxis.permission.PermissionPerson;
        Factory.registerPermission("Person", praxis.permission.PermissionPerson);
        import praxis.permission.PermissionPatient;
        Factory.registerPermission("Patient", praxis.permission.PermissionPatient);
        import praxis.permission.PermissionStammdaten;
        Factory.registerPermission("Stammdaten", praxis.permission.PermissionStammdaten);
        import praxis.permission.PermissionKalender;
        Factory.registerPermission("Kalender", praxis.permission.PermissionKalender);
        import praxis.permission.PermissionHauptMenu;
        Factory.registerPermission("HauptMenu", praxis.permission.PermissionHauptMenu);
    }



// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}
