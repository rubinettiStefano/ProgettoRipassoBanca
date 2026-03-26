package entities;

import libreria.Console;

public class Conto {

    public String numeroConto;
    public String pin;

    public Conto(String n,String p)
    {
        numeroConto=n;
        pin=p;
    }


    public boolean provaAccesso(String nC, String p) {

        return nC.equals(numeroConto) && p.equals(pin);
    }
}
