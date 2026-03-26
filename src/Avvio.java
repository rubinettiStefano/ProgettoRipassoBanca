import libreria.Console;
import entities.Conto;

void main() {
    Conto[] conti = new Conto[]{
            new Conto("A1", "12345", 1500.00),
            new Conto("B2", "98765", 4230.00),
            new Conto("D3", "55443", 890.00),
            new Conto("Z4", "11223", 9450.00),
            new Conto("T5", "67890", 120.00),
            new Conto("H6", "13579", 5600.00),
            new Conto("A7", "24680", 3100.00),
            new Conto("R8", "99887", 750.00),
            new Conto("A9", "44556", 8200.00),
            new Conto("P10", "77665", 2345.00)
    };

    Conto mio = null;
    Console.print("Benvenuto, ora fai login");
    do {
        mio = login(conti);
    } while (mio == null);
    Console.print("Login effettuato per conto " + mio);

    int cmd = 0;
    do {
        Console.print("\nInserisci il numero del comando (0 per la lista): ");
        cmd = Console.readInt();

        switch (cmd) {
            case 0 -> help();
            case 1 -> stampaConto(mio);
            case 2 -> stampaResoconto(mio);
            case 3 -> faiPrelievo(mio);
            case 4 -> versaAssegno(mio);
            case 5 -> versaBanconote(mio);
            case 6 -> richiediPrestito(mio);
            case 7 -> faiBonifico(mio,conti);
            case 99 -> mio = cambiaConto(conti, mio);
            case -1 -> Console.print("Uscita dal programma. Buon lavoro!\n");
            default -> Console.print("Comando non presente. Riprova.\n");
        }

    } while (cmd != -1);
}

void faiBonifico(Conto mio, Conto[] conti)
{
    Conto destinatario = ricercaContoPerNumero(conti);
    Console.print("Immetti valore bonifico");
    double val = Console.readDouble();
    mio.bonifico(destinatario,val);
}

private void richiediPrestito(Conto mio)
{
    Console.print("Quanto vuoi di prestito");
    int val = Console.readInt();
    Console.print("Per quanti anni");
    int anni = Console.readInt();
    mio.chiediPrestito(val,anni);
}

void versaBanconote(Conto mio)
{
    Console.print("Quante banconote vuoi versare");
    int nBanconote = Console.readInt();

    int[] banconote = new int[nBanconote];
    Console.print("Ora inserirai "+nBanconote +" banconote, metti:"+"\n"+
                    "1 - 5 euro\n"+
                    "2 - 10 euro\n"+
                    "3 - 20 euro\n"+
                    "4 - 50 euro\n"+
                    "5 - 100 euro\n"+
                    "6 - 500 euro"
    );
    for (int i = 0; i <banconote.length ; i++)
    {
        boolean ripeti=false;
        do {
            Console.print("Inserisci numero per banconota");
            int n = Console.readInt();
            switch (n)
            {
                case 1->banconote[i]=5;
                case 2->banconote[i]=10;
                case 3->banconote[i]=20;
                case 4->banconote[i]=50;
                case 5->banconote[i]=100;
                case 6->banconote[i]=500;
                default ->
                        {
                            Console.print("numero non valido");
                            ripeti=true;
                        }
            }
        }while (ripeti && banconote[i]==0);
    }
    mio.versamentoBanconote(banconote);
}



private void versaAssegno(Conto mio)
{
    Console.print("Quanto vuoi versare");
    int val = Console.readInt();
    mio.versamentoAssegno(val);
}

void faiPrelievo(Conto mio)
{
    Console.print("Quanto vuoi prelevare");
    int val = Console.readInt();
    mio.prelievo(val);
}

void stampaResoconto(Conto mio)
{
    if(mio.resoconto.equalsIgnoreCase(""))
        Console.print("Non hai ancora effettuato operazioni");
    else
    {
        Console.print("Ecco il resoconto delle tue operazioni");
        Console.print(mio.resoconto);
    }
}

void stampaConto(Conto mio)
{
    Console.print("Ecco il tuo conto");
    Console.print(mio+"\n");
}

Conto cambiaConto(Conto[] arr, Conto oldConto) {
    Console.print("Inserisci dati del conto con cui vuoi entrare");
    Conto res = login(arr);
    if (res == null)
        return oldConto;
    else
    {
        Console.print("Hai fatto login con "+res);
        return res;
    }
}

Conto login(Conto[] arr) {
    Console.print("Dammi numero conto");
    String n = Console.readString();
    Console.print("INSERISCI PIN");
    String pin = Console.readString();

    Conto res = null;
    for (int i = 0; i < arr.length; i++) {
        if (arr[i].numeroConto.equalsIgnoreCase(n))
            res = arr[i];
    }

    if (res == null) {
        Console.print("NUMERO NON ESISTENTE");
        return null;
    }

    if (res.provaAccesso(n, pin)) {
        return res;
    } else {
        Console.print("PIN SBAGLIATO");
        return null;
    }
}

void help() {
    String commands = """
            =================== MENU BANCOMAT ===================
             0 - HELP (Mostra questo menu)
             1 - Visualizza dati del conto
             2 - Visualizza resoconto/estratto conto
             3 - Effettua un prelievo
             4 - Versa un assegno
             5 - Versa banconote
             6 - Richiedi un prestito
             7 - Effettua un bonifico
            99 - Cambia utente/conto
            -1 - Termina programma
            =====================================================
            """;
    Console.print(commands);
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