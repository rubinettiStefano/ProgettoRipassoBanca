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

    public void versamentoAssegno(int valoreVersamento)
    {
        if(valoreVersamento>=2000)
        {
            Console.print("WE PAPERONE,PER VERSAMENTI DEL GENERE VAI IN FILIALE");
            return;
        }
        saldo+=valoreVersamento;
        Console.print("Operazione conclusa con successo, nuovo saldo : "+saldo);
    }

    //banconote = {10,20,10,50};
    public void versamentoBanconote(int[] banconote)
    {
        if(banconote.length>5)//non più di 5 banconote per volta
        {
            Console.print("Non abbiamo tutto il giorno, vai in filiale");
            return;
        }

        //facciamo finta che il mio saldo iniziale valga 40
        //banconote.length ->4
        //primo giro
        //i->0      banconote[i]->10       saldo->40     fine primo giro -> 50
        //secondo giro
        //i->1      banconote[i]->20       saldo->50     fine primo giro -> 70
        //terzo giro
        //i->2      banconote[i]->10       saldo->70     fine primo giro -> 80
        //quarto giro
        //i->3     banconote[i]->50       saldo->80     fine primo giro -> 130
        //quinto giro
        //i->4         i < banconote.length  diventa false , poichè 4<4, termina

        for (int i = 0; i < banconote.length; i++)
            saldo+=banconote[i];//saldo=banconote[i]+saldo;
        //banconote[i]+=saldo //banconote[i]=banconote[i]+saldo;

        Console.print("Operazione conclusa con successo, nuovo saldo : "+saldo);
    }
}
