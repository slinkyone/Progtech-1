package személyszállítás;

import java.util.LinkedList;
import java.util.List;

public class Varos{

    private List<Jarmu> jarmuvek;
    private static int bevetel = 0;
    private boolean tartozik_e_orszaghoz = false;
    
    //Varos inicializalas
    public Varos() {
        jarmuvek = new LinkedList<>();
    }
    
    //Bevetel hozzáadas
    public static void addBevetel(int bevetel) {
        Varos.bevetel = bevetel;
    }

    //Bevétel lekérdezés
    public static int actualBevetel() {
        return bevetel;
    }
    
    //Tartozik-e orszaghoz
    public void Tartozik_e_orszaghoz(boolean tartozik_e_orszaghoz) {
        this.tartozik_e_orszaghoz = tartozik_e_orszaghoz;
    }
    
    //Jármű hozzáadaása
    public void addJarmu(Jarmu jarmu) {
        jarmuvek.add(jarmu);
        jarmu.addTartozik_e_varoshoz(true);
    }

    //Jármű eltávolítása
    public void removeJarmu(Jarmu jarmu) {
        jarmuvek.remove(jarmu);
        jarmu.addTartozik_e_varoshoz(false);
    }
}
