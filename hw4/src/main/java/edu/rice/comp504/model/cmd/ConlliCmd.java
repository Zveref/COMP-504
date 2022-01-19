package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

public interface ConlliCmd {

    public void collide(APaintObject Obj1, APaintObject Obj2);

}
