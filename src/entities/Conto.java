package entities;

import libreria.Console;

public class Conto {

    public String numeroConto;
    public String pin;
    public double saldo;

    public Conto(String n,String p,double saldoIniziale)
    {
        numeroConto=n;
        pin=p;
        saldo=saldoIniziale;
    }


    public boolean provaAccesso(String nC, String p) {

        return nC.equals(numeroConto) && p.equals(pin);
    }

    public void prelievo(int valorePrelievoBancomat)
    {
        if(valorePrelievoBancomat>saldo)
        {
            Console.print("Il tuo saldo rimante è inferiore al valore che vuoi prelevare");
            return;//return; nei metodi VOID TERMINA ESECUZIONE METODO
        }

        saldo-=valorePrelievoBancomat;
        Console.print("Operazione conclusa con successo, saldo rimanente: "+saldo);
    }
}
