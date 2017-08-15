package com.developerphil.liba;

import com.developerphil.libb.Greeter;

public class HelloWorld {

    public CharSequence getString() {
        return "Hello " + new Greeter().getWho();
    }

}
