package altropackage;

import libreria.Console;
import visibilita.Persona;

public class MainAltroPackage
{
    static void main() {
        Persona p = new Persona();
        p.nome = "Stefano";
        p.anni = 30;
        Console.print(p.nome);
    }
}
