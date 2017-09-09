package személyszállítás;

public class Vonat extends Jarmu{

    public Vonat(int ferohely) {
        super(ferohely);
    }

    @Override
    public void addVezeto(Vezeto sofor) {
        if(Tartozik_e_varoshoz() == false) return;
        if(sofor.utazike == 1) return;
        for(int i = 0; i < sofor.jogositvany.size(); i++){
            if("vonat".equals(sofor.jogositvany.get(i).toString())){
                this.vezeto=sofor;
                sofor.utazike=1;
            }
        }
    }
}
