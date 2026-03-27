import libreria.Console;
import visibilita.Libro;

void main()
{
    Libro b = new Libro();
    boolean isbnGiusto;
    do {
        Console.print("Dammi isbn libro");
        String in = Console.readString();
        isbnGiusto = b.setIsbn(in);
    }while (!isbnGiusto);

}