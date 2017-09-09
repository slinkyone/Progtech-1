package személyszállítás;

public class Ember {

    private String nev;
    int utazike = 0;
    
    //Konstruktor
    public Ember(String nev) {
        this.nev = nev;
    }

    //Nev lekerdezes
    public String getNev() {
        return nev;
    }
}
