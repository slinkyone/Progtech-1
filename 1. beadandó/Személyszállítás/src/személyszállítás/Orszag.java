package személyszállítás;

import java.util.LinkedList;
import java.util.List;

public class Orszag {

    private List<Varos> varosok;
    private boolean tartozik_e_varoshoz = false;

    public Orszag() {
        varosok = new LinkedList<>();
    }

    //Varos hozzaadasa az orszaghoz
    public void addVaros(Varos varos) {
        varosok.add(varos);
        varos.Tartozik_e_orszaghoz(true);
    }

    //Varos eltavolitasa az orszagbol
    public void removeVaros(Varos varos) {
        varosok.remove(varos);
        varos.Tartozik_e_orszaghoz(false);
    } 
}