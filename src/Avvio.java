import libreria.Console;
import entities.Conto;

void main() {
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
            case 99 -> mio = cambiaConto(conti, mio);
            case -1 -> Console.print("Uscita dal programma. Buon lavoro!\n");
            default -> Console.print("Comando non presente. Riprova.\n");
        }

    } while (cmd != -1);
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
            =================== MENU ESERCIZI ===================
            -1 - Termina programma
             0 - HELP (Mostra questo menu)
            =====================================================
            """;
    Console.print(commands);
}