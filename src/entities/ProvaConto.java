import entities.Conto;
import libreria.Console;

/*void main()
{


    Conto c = new Conto("A1","12345",100);


//    Console.print("Inserisci numero conto");
//    String n = Console.readString();
//    Console.print("Inserisci pin");
//    String p = Console.readString();
//
//    Console.print("Accesso "+(c.provaAccesso(n,p)?"consentito":"negato"));

    //permettere e impostare saldo a 30
    c.prelievo(70);

    c.versamentoAssegno(3000);
    int[] mieiSoldi = {50,500,200,10};
    c.versamentoBanconote(mieiSoldi);

    //negare
    c.prelievo(40);

    //stampare 30
    Console.print(c.saldo);
}*/

void main()
{
    Conto[] conti = {
            new Conto("A1", "12345", 1500.50),
            new Conto("B2", "98765", 4230.00),
            new Conto("D3", "55443", 890.25),
            new Conto("Z4", "11223", 9450.00),
            new Conto("T5", "67890", 120.75),
            new Conto("H6", "13579", 5600.00),
            new Conto("A7", "24680", 3100.40),
            new Conto("R8", "99887", 750.00),
            new Conto("A9", "44556", 8200.15),
            new Conto("P10", "77665", 2345.60)
    };
    effettuaBonifico(conti);
}


void effettuaBonifico(Conto[] arr)
{
    //il mio conto per esempio ora è questo sotto fisso
    Conto mio = new Conto("S1","12345",1000);
    Console.print("Benvenuto, ora facciamo bonifico, dammi cifra");
    double cifra = Console.readDouble();
    Conto destinatario = ricercaContoPerNumero(arr);
    mio.bonifico(destinatario,cifra);
}

Conto ricercaContoPerNumero(Conto[] arr)
{
    Console.print("Dammi numero conto destinatario");
    String n = Console.readString();

    //1 - Variabile null
    Conto res = null;
    for (int i = 0; i < arr.length; i++)
    {
        if(arr[i].numeroConto.equalsIgnoreCase(n))
            res=arr[i];
    }
    //posso restituire o un conto o null
    return res;
}