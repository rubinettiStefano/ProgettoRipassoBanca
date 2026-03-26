package entities;

import libreria.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Conto {

    public String numeroConto;
    public String pin;
    public double saldo;
    public String resoconto = "";

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
            aggiungiFallimetoAlResoconto("Prelievo",valorePrelievoBancomat,"SALDO INSUFFICIENTE");
            return;//return; nei metodi VOID TERMINA ESECUZIONE METODO

        }

        saldo-=valorePrelievoBancomat;
        aggiungiAlResoconto("Prelievo",valorePrelievoBancomat,"");
        Console.print("Operazione conclusa con successo, saldo rimanente: "+saldo);
    }

    public void versamentoAssegno(int valoreVersamento)
    {
        if(valoreVersamento>=2000)
        {
            Console.print("WE PAPERONE,PER VERSAMENTI DEL GENERE VAI IN FILIALE");
            aggiungiFallimetoAlResoconto("Versamento Assegno",valoreVersamento,"CIFRA TROPPO GRANDE");

            return;
        }
        saldo+=valoreVersamento;
        aggiungiAlResoconto("Versamento Assegno",valoreVersamento,"");

        Console.print("Operazione conclusa con successo, nuovo saldo : "+saldo);
    }

    //banconote = {10,20,10,50};
    public void versamentoBanconote(int[] banconote)
    {
        if(banconote.length>5)//non più di 5 banconote per volta
        {
            Console.print("Non abbiamo tutto il giorno, vai in filiale");
            aggiungiFallimetoAlResoconto("Versamento Bancomat",0,"TROPPE BANCONOTE");

            return;
        }

        int sommaValori = 0;
        for (int i = 0; i < banconote.length; i++)
            sommaValori+=banconote[i];

        saldo+=sommaValori;

        aggiungiAlResoconto("Versamento Bancomat",sommaValori," Numero banconote: "+banconote.length);

        Console.print("Operazione conclusa con successo, nuovo saldo : "+saldo);
    }

    public void bonifico(Conto destinatario,double valoreBonifico)
    {
        if(saldo<valoreBonifico)
        {
            Console.print("NO SOLDI,NON FACCIO BONIFICO");
            aggiungiFallimetoAlResoconto("Bonifico",valoreBonifico,"NO SOLDI");

            return;
        }
        if(destinatario==null)
        {
            Console.print("NO DESTINATARIO,CONTROLLA DATI");
            aggiungiFallimetoAlResoconto("Bonifico",valoreBonifico,"NO DESTINATARIO");

            return;
        }
        if(destinatario.numeroConto.equalsIgnoreCase(numeroConto))
        {
            Console.print("CHE TI FAI IL BONIFICO DA SOLO");
            aggiungiFallimetoAlResoconto("Bonifico",valoreBonifico,"AUTOBONIFICO");

            return;
        }

        saldo-=valoreBonifico;
        destinatario.saldo+=valoreBonifico;
        aggiungiAlResoconto("Versamento Bancomat",valoreBonifico,"Destinatario: "+destinatario.numeroConto);
        Console.print("Operazione conclusa con successo, nuovo saldo : "+ saldo);
    }

    public void aggiungiAlResoconto(String tipoOperazione,double valoreOperazione,String commentoOpzionale)
    {
        //LocalDateTime.now() vi da data e ora attuale
        resoconto+= LocalDateTime.now()+" - "+tipoOperazione+ "- Euro "+valoreOperazione;
        if(commentoOpzionale!=null && !commentoOpzionale.equalsIgnoreCase(""))
            resoconto+=" - "+commentoOpzionale;
        resoconto+=" - saldo attuale "+saldo+"\n";
    }

    public void aggiungiFallimetoAlResoconto(String tipoOperazione,double valoreOperazione,String motivoFallimento)
    {
        //LocalDateTime.now() vi da data e ora attuale
        resoconto+= LocalDateTime.now()+" - "+tipoOperazione+ " FALLITO - Euro "+valoreOperazione+" - Motivo Fallimento :"+motivoFallimento+" - saldo attuale "+saldo+"\n";
    }

    public String toString()
    {
        return "Conto numero: "+numeroConto+" con saldo "+saldo;
    }
}
