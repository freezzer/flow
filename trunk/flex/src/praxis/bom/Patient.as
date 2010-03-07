// @Generated(flex/src/praxis/bom/Patient.as)
/* 

           generated code by flow "flex on wings"
           this code is property of Andreas Marochow
    
*/ 

package praxis.bom {
import mx.collections.ArrayCollection;
import de.ama.framework.data.*;
import de.ama.framework.util.*;
[RemoteClass(alias="praxis.bom.Patient")]
public class Patient  extends BusinessObject { 

    public var vorname:String;

    public var nachname:String;

    public var telefon:String;

    public var email:String;

    public var status:String;

    public var kasse:String;

    public var beruf:String;

    public var geschlecht:String;

    public var erfasst:String;

    public var adresse:Adresse = new Adresse();


    public var eintraege:Array = Util.createArray("praxis.bom.Eintrag",1);



// *****************************************************************************************
// [MANUAL.CODE] insert manual code here
// *****************************************************************************************
// [MANUAL.CODE.END] 

}}
