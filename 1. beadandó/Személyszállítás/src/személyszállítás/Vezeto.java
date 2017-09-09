package személyszállítás;

import java.util.LinkedList;
import java.util.List;

public class Vezeto extends Ember {

    public List<Jarmuvek> jogositvany;

    //Vezeto inicializal
    public Vezeto(String nev) {
        super(nev);
        jogositvany = new LinkedList<>();
    }

    //Jogositvany hozzarendeles
    public void addJogositvany(Jarmuvek jarmu) {
        jogositvany.add(jarmu);
    }
}
