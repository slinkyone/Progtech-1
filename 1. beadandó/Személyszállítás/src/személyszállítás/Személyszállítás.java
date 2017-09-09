package személyszállítás;

/* Egy jegy ára 400ft, bérlet ára 8000 büntetés esetenként 5000ft
 */
public class Személyszállítás {

    public static void main(String[] args) {
        //Város és ország létrehozása és város hozzárendelése országhoz       
        Varos budapest = new Varos();
        Orszag hungary = new Orszag();
        hungary.addVaros(budapest);
        
        //Vonat létrehozása és hozzárendelése budapesthez
        Vonat vonat1 = new Vonat(2); // 2 férőhelye van a vonatnak;
        budapest.addJarmu(vonat1);
        
        //Üres vonat szimulálása
        System.out.println("Bevétel: " + Varos.actualBevetel());
        System.out.println("Szabad helyek száma: " + vonat1.szabadulohelyekSzama() + "\n");

        //Ellenőr, vezető, vezetői engedély, utas létrehozása
        Ellenor ellenor1 = new Ellenor("Jóska az Ellenőr");
        Vezeto vezeto1 = new Vezeto("Vonat Vezető Béla");
        vezeto1.addJogositvany(Jarmuvek.VONAT);
        Utas utas1 = new Utas("Ya man Rastaman");
        
        //Ellenőr, vezető, vezetői engedély, utas hozzáadása mivel nincs jegye ezért megbüntették
        System.out.println("Ellenőr, vezető, vezetői engedély, utas hozzáadása mivel nincs jegye ezért megbüntették");
        vonat1.addEllenor(ellenor1);
        vonat1.addVezeto(vezeto1);
        vonat1.addUtas(utas1);
        System.out.println();
        
        //Utas meg lett büntetve ezért lett bevétel
        System.out.println("Utas meg lett büntetve ezért lett bevétel");
        System.out.println("Szabad helyek száma: " + vonat1.szabadulohelyekSzama());
        System.out.println("Bevétel: " + Varos.actualBevetel() + "\n");
        
        //Utas leszáll
        System.out.println("Utas leszáll");
        vonat1.removeUtas(utas1);
        System.out.println("Szabad helyek száma: " + vonat1.szabadulohelyekSzama() + "\n");
        
        //Utas vesz jegyet és nem büntetik meg
        System.out.println("Utas vesz jegyet és nem büntetik meg");
        utas1.jegyVasarlas();
        System.out.println("Bevétel: " + Varos.actualBevetel() + "\n");
        
        //Utas visszaszáll
        System.out.println("Utas visszaszáll");
        vonat1.addUtas(utas1);
        System.out.println("Szabad helyek száma: " + vonat1.szabadulohelyekSzama() + "\n");
    }

}
