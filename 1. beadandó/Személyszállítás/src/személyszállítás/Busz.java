package személyszállítás;

public class Busz extends Jarmu {

    public Busz(int ferohely) {
        super(ferohely);
    }

    @Override
    public void addVezeto(Vezeto sofor) {
        if (sofor.utazike == 1) {
            return;
        }
        if (Tartozik_e_varoshoz() == false) {
            return;
        }
        for (int i = 0; i < sofor.jogositvany.size(); i++) {
            if ("busz".equals(sofor.jogositvany.get(i).toString())) {
                this.vezeto = sofor;
                sofor.utazike = 1;
            }
        }
    }
}