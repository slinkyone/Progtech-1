package személyszállítás;

import java.util.LinkedList;
import java.util.List;

public abstract class Jarmu {

    private int ulohely;
    private int uresulohelyek;
    protected Vezeto vezeto;
    private List<Utas> utasok;
    private List<Ellenor> ellenorok;
    private boolean tartozik_e_varoshoz = false;
    
    //Konstruktor
    public Jarmu(int ulohely) {
        this.ulohely = ulohely;
        this.uresulohelyek = ulohely;
        utasok = new LinkedList<>();
        ellenorok = new LinkedList<>();
    }
    
    //Varoshoz tartozas lekerdezese
    public boolean Tartozik_e_varoshoz() {
        return tartozik_e_varoshoz;
    }

    //Varoshoz tartozas modositasa
    public void addTartozik_e_varoshoz(boolean tartozik_e_varoshoz) {
        this.tartozik_e_varoshoz = tartozik_e_varoshoz;
    }
    
    //Vezeto hozzaadasa
    public abstract void addVezeto(Vezeto vezeto);

    //Vezeto eltavolitasa
    public void removeVezeto() {
        vezeto.utazike = 0;
        this.vezeto = null;
    }

    //Utas hozzaadasa
    public void addUtas(Utas utas) {
        if (vezeto == null) {
            return;
        }
        if (utas.utazike == 1) {
            return;
        }
        if (uresulohelyek < 1) {
            return;
        }
        utasok.add(utas);
        uresulohelyek--;
        utas.utazike = 1;
        Varos varos = null;
        
        if (utas.Van_e_jegy() < 1 && utas.van_e_berlet == false && ellenorok.size() > 0) {
            System.out.println(utas.getNev() + "-t megbüntették, mert nem volt érvényes jegye vagy bérlete.");
            varos.addBevetel(varos.actualBevetel() + 5000);
        }
    }

    //Utas eltavolitasa
    public void removeUtas(Utas utas) {
        if (utas.utazike == 0) {
            return;
        }
        utasok.remove(utas);
        utas.utazike = 0;
        uresulohelyek++;
        
        if (utas.Van_e_jegy() > 0) {
            utas.jegyHozzaad(utas.Van_e_jegy() - 1);
        }
    }

    //Ellenor hozzaadasa
    public void addEllenor(Ellenor ellenor) {
        if (ellenor.utazike == 1) {
            return;
        }
        ellenorok.add(ellenor);
        ellenor.utazike = 1;
    }
    
    //Ellenor leszallitasa
    public void removeEllenor(Ellenor ellenor) {
        if (ellenor.utazike == 0) {
            return;
        }
        ellenorok.remove(ellenor);
        ellenor.utazike = 0;
    }

    //Ulohelyek szamanak lekerdezese
    public int szabadulohelyekSzama() {
        return uresulohelyek;
    }
}
