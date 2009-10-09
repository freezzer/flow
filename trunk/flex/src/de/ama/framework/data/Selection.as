package de.ama.framework.data {

public class Selection {
    public  var oidString:String;

    public function Selection(data:Data = null) {
        if (data!=null) {
            setData(data);
        }
    }

    public function setData(data:Data) :void{
        this.oidString = data.oidString;
    }

}
}