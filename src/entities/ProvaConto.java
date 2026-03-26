import entities.Conto;
import libreria.Console;

void main()
{
    Conto c = new Conto("A1","12345");

    Console.print("Inserisci numero conto");
    String n = Console.readString();
    Console.print("Inserisci pin");
    String p = Console.readString();

    Console.print("Accesso "+(c.provaAccesso(n,p)?"consentito":"negato"));
}