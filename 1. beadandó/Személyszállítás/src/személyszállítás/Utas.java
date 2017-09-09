package személyszállítás;

public class Utas extends Ember {

    private int van_e_jegy = 0;
    boolean van_e_berlet = false;

    //Konstruktor
    public Utas(String nev) {
        super(nev);
    }

    //Jegy vasarlas
    public void jegyVasarlas() {
        van_e_jegy++;
        Varos.addBevetel(Varos.actualBevetel() + 400);
    }

    //Berlet vasarlas
    public void berletVasarlas() {
        van_e_berlet = true;
        Varos.addBevetel(Varos.actualBevetel() + 8000);
    }
    
    //Jegy hozzarendeles
    public void jegyHozzaad(int van_e_jegy) {
        this.van_e_jegy = van_e_jegy;
    }

    //Van e jegye
    public int Van_e_jegy() {
        return van_e_jegy;
    }
}
