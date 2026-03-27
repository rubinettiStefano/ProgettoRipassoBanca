package visibilita;

import libreria.Console;

public class Libro {
    //AAA-A-AA-AAAAAA-A
    private String isbn;//codice univoco libro
    private String genre;
    private int nPages;
    private double price;
    private String coverColor;
    private String title;
    private String author;
    private String esbr;

    public String getIsbn() {
        return returnEmptyOrString(isbn);
    }

    /**
     * Metodo setter che imposta il vlaore dell'ISBN del libro
     *
     * @param nuovoIsbn la stringa in arrivo, formatta si spera giusta
     * @return true se la stringa è buona, e la inserisce nella proprietà <br>
     * false se la stringa è brutta, non cambia la proprietà
     */
    public boolean setIsbn(String nuovoIsbn) {

        //AAA-AA-AA-AAAAA-A
        if (nuovoIsbn == null || nuovoIsbn.length() != 17) {
            Console.print("BRUTTO,NULLO O DI DIMENSIONE SBAGLIATA");
            return false;
        }
        //controllo con regex o regexp
//        if(nuovoIsbn.matches("\\d{3}-\\d{2}-\\d{2}-\\d{5}-\\d"))

        //SPLIT delle STRING
        //prende come parametro 1 o più caratteri
        //ogni volta che li trova nella stringa la spezza
        //producendo quindi un array di String

        //nuovoIsbn ="978-88-04-77182-1"
        String[] splittato = nuovoIsbn.split("-");
        //splittato[0] = "978";
        //splittato[1] = "88";
        //splittato[2] = "04";
        //splittato[3] = "77182";
        //splittato[4] = "1";

        if (splittato.length != 5) {
            Console.print("Il numero di - nell'ISBN è sbagliato");
            return false;
        }

        if (splittato[0].length() != 3) {
            Console.print("Primo blocco di dimensione sbagliata");
            return false;
        }
        if (splittato[1].length() != 2) {
            Console.print("Secondo blocco di dimensione sbagliata");
            return false;
        }
        if (splittato[2].length() != 2) {
            Console.print("Terzo blocco di dimensione sbagliata");
            return false;
        }
        if (splittato[3].length() != 5) {
            Console.print("Quarto blocco di dimensione sbagliata");
            return false;
        }
        if (splittato[4].length() != 1) {
            Console.print("Ultimo blocco di dimensione sbagliata");
            return false;
        }
        //             0 1 2 3 4 5 6 7
        //String s = " p a p e r i n o";
        //s.charAt(3); -> 'e'


        for (int i = 0; i < splittato.length; i++) {
            String blocco = splittato[i];
            for (int j = 0; j < blocco.length(); j++) {
                //prendiamo singolo carattere alla posizione j del blocco
                char c = blocco.charAt(j);
                //se c NON è una cifra numerica
                //if(!Character.isDigit(c))
                if (c < 48 || c > 57)//48 equivale a 0, 57 a 9
                {
                    Console.print("C'è una lettera nel codice");
                    return false;
                }
            }
        }

        isbn = nuovoIsbn;
        return true;
    }

    public String getGenre() {
        return returnEmptyOrString(genre);
    }

    public boolean setGenre(String nuovoGenere) {
        if (nuovoGenere == null || nuovoGenere.length() < 3) {
            Console.print("Dai dammi un genere buono");
            return false;
        }
        genre = nuovoGenere;
        return true;
    }

    public boolean setPrice(double nuovoPrezzo) {
        if (nuovoPrezzo < 0) {
            Console.print("Non facciamo beneficenza");
            return false;
        }
        price = nuovoPrezzo;
        return true;
    }

    public boolean setNPages(int nuovePagine) {
        if (nPages <= 0) {
            Console.print("Che è un libro senza pagine?");
            return false;
        }
        nPages = nuovePagine;
        return true;
    }


    public int getnPages() {
        return nPages;
    }


    public double getPrice() {
        return price;
    }

    public String getCoverColor() {
        return returnEmptyOrString(coverColor);

    }

    public boolean setCoverColor(String coverColor) {
        if (coverColor == null || coverColor.length() < 3) {
            Console.print("Dai dammi un coverColor buono");
            return false;
        }
        this.coverColor = coverColor;

        return true;
    }

    public String getTitle() {
        return returnEmptyOrString(title);
    }

    public boolean setTitle(String title) {

        if (title == null || title.length() < 3) {
            Console.print("Dai dammi un title buono");
            return false;
        }
        this.title = title;
        return true;
    }

    public String getAuthor() {
        return returnEmptyOrString(author);
    }

    public boolean setAuthor(String author) {
        if (author == null || author.length() < 3) {
            Console.print("Dai dammi un title buono");
            return false;
        }
        this.author = author;
        return true;
    }

    public String getEsbr() {
        return esbr==null || esbr.equals("") ? "RP":esbr;
    }

    public String getPEGI() {
        String res="?";
        switch (esbr)
        {
            case "E" -> res="3+";
            case "C" -> res="10+";
            case "T" -> res="13+";
            case "M" -> res="17+";
            case "A" -> res="18+";
        }
        return res;
    }

    public boolean setEsbr(String esbr)
    {
        if(esbr==null || esbr.length()!=1)
        {
            Console.print("ESBR NULLO O NON ACCETTABILE");
            return false;
        }

        String[] valoriAccettabili = {"E", "C", "T", "M", "A"};
        esbr = esbr.toUpperCase();
        boolean accettato = false;
        for (int i = 0; i < valoriAccettabili.length; i++) {
            if (esbr.equals(valoriAccettabili[i]))
                accettato = true;
        }

        if(!accettato)
        {
            Console.print("ESBR NON ESISTENTE");
            return false;
        }

        this.esbr = esbr;
        return true;
    }

    private String returnEmptyOrString(String s) {
        return s == null ? "" : s;
    }


}
