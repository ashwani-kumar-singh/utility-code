package functionalinterface.impl;

import functionalinterface.Bird;

public class Eagle implements Bird {
    @Override
    public void canFly() {
        System.out.println("Eagle Bird Implementation");
    }
}
