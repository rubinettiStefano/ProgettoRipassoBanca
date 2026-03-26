package entities;

import libreria.Console;

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
            aggiungiFallimentoAlResoconto("Prelievo",valorePrelievoBancomat,"SALDO INSUFFICIENTE");
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
            aggiungiFallimentoAlResoconto("Versamento Assegno",valoreVersamento,"CIFRA TROPPO GRANDE");

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
            aggiungiFallimentoAlResoconto("Versamento Bancomat",0,"TROPPE BANCONOTE");

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
            aggiungiFallimentoAlResoconto("Bonifico",valoreBonifico,"NO SOLDI");

            return;
        }
        if(destinatario==null)
        {
            Console.print("NO DESTINATARIO,CONTROLLA DATI");
            aggiungiFallimentoAlResoconto("Bonifico",valoreBonifico,"NO DESTINATARIO");

            return;
        }
        if(destinatario.numeroConto.equalsIgnoreCase(numeroConto))
        {
            Console.print("CHE TI FAI IL BONIFICO DA SOLO");
            aggiungiFallimentoAlResoconto("Bonifico",valoreBonifico,"AUTOBONIFICO");

            return;
        }

        saldo-=valoreBonifico;
        destinatario.saldo+=valoreBonifico;
        aggiungiAlResoconto("Bonifico",valoreBonifico,"Destinatario: "+destinatario.numeroConto);
        Console.print("Operazione conclusa con successo, nuovo saldo : "+ saldo);
    }

    public void chiediPrestito(int valorePrestito,int anni)
    {
        if(anni>3)
        {
            Console.print("No, massimo 3 anni");
            aggiungiFallimentoAlResoconto("Prestito",valorePrestito,"PRESTITO TROPPO LUNGO");
            return;
        }
        if(valorePrestito>10000*anni)
        {
            Console.print("No, massimo 10k euro all'anno");
            aggiungiFallimentoAlResoconto("Prestito",valorePrestito,"PRESTITO ECCESSIVO");
            return;
        }
        saldo+=valorePrestito;

        double valoreConInteressi = valorePrestito+(valorePrestito*0.2*anni);
        int numeroRate = anni*12;
        double rata = valoreConInteressi/numeroRate;

        String stampa = "Prestito da "+valorePrestito+" da restituire in "+numeroRate+" comode rate da "+rata+" euro.\n"+
                        "Il totale da risarcire è quindi di "+valoreConInteressi+" euro in "+anni+" anni";
        aggiungiAlResoconto("Prestito",valorePrestito,"Da risarcire in "+anni);
        Console.print(stampa);
    }

    public void aggiungiAlResoconto(String tipoOperazione,double valoreOperazione,String commentoOpzionale)
    {
        //LocalDateTime.now() vi da data e ora attuale
        resoconto+= LocalDateTime.now()+" - "+tipoOperazione+ "- Euro "+valoreOperazione;
        if(commentoOpzionale!=null && !commentoOpzionale.equalsIgnoreCase(""))
            resoconto+=" - "+commentoOpzionale;
        resoconto+=" - saldo attuale "+saldo+"\n";
    }

    public void aggiungiFallimentoAlResoconto(String tipoOperazione, double valoreOperazione, String motivoFallimento)
    {
        //LocalDateTime.now() vi da data e ora attuale
        resoconto+= LocalDateTime.now()+" - "+tipoOperazione+ " FALLITO - Euro "+valoreOperazione+" - Motivo Fallimento :"+motivoFallimento+" - saldo attuale "+saldo+"\n";
    }

    public String toString()
    {
        return "Conto numero: "+numeroConto+" con saldo "+saldo;
    }
}
