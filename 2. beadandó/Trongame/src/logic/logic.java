package logic;

public class logic {

    private int playernumber, playernumber2;
    private final int size;
    private final int[][] map;
    private int x, y, x2, y2 = 0;
    private boolean end = false;
    private int looser;
    
    public logic(int size) {
        this.size = size;
        this.map = new int[size][size];
        this.x = this.size / 2;
        this.y = 0;
        this.x2 = this.size / 2;
        this.y2 = this.size - 1;
    }

    public void setNumber(int playernumber) {this.playernumber = playernumber;}
    public void setNumber2(int playernumber2) {this.playernumber2 = playernumber2;}

    public int getNumber() {return playernumber;}
    public int getNumber2() {return playernumber2;}
    
    public int getLooser() {return looser;}
    public int getSize() {return size;}
    public int getMapValue(int x, int y) {return map[x][y];}
    
    public boolean isEnd() {return end;}

    //create buttonmap
    public final void newGame() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = 0;
            }
        }
        //set bikes to start position
        map[size / 2][0] = 1;
        map[size / 2][size - 1] = 2;
    }

    //@param c (move direction up,down,left,right), playernumber 0 or 1 (0 is the left bike, 1 is the right bike) 
    public void moveDirection(char c, int playernumber) {
        switch (c) {
            case 'u':
                if (x > 0 && this.map[x - 1][y] != 3 && this.map[x - 1][y] != 4 && playernumber == 1) {
                    this.map[x][y] = 4;
                    this.x--;
                    this.map[x][y] = 1;
                } else if (x2 > 0 && this.map[x2 - 1][y2] != 3 && this.map[x2 - 1][y2] != 4 && playernumber == 0) {
                    this.map[x2][y2] = 3;
                    this.x2--;
                    this.map[x2][y2] = 2;
                } else {
                    end = true;
                    if (playernumber == 1){ looser = 0;}
                    if (playernumber == 0){ looser = 1;}
                }
            break;
            case 'd':
                if (x < size - 1 && map[x + 1][y] != 3 && map[x + 1][y] != 4 && playernumber == 1) {
                    map[x][y] = 4;
                    this.x++;
                    map[x][y] = 1;
                } else if (x2 < size - 1 && map[x2 + 1][y2] != 3 && map[x2 + 1][y2] != 4 && playernumber == 0) {
                    map[x2][y2] = 3;
                    this.x2++;
                    map[x2][y2] = 2;
                } else {
                    end = true;
                    if (playernumber == 1){ looser = 0;}
                    if (playernumber == 0){ looser = 1;}
                }
            break;
            case 'l':
                if (y > 0 && map[x][y - 1] != 3 && map[x][y - 1] != 4 && playernumber == 1) {
                    map[x][y] = 4;
                    this.y--;
                    map[x][y] = 1;
                } else if (y2 > 0 && map[x2][y2 - 1] != 3 && map[x2][y2 - 1] != 4 && playernumber == 0) {
                    map[x2][y2] = 3;
                    this.y2--;
                    map[x2][y2] = 2;
                } else {
                    end = true;
                    if (playernumber == 1){ looser = 0;}
                    if (playernumber == 0){ looser = 1;}
                }
            break;
            case 'r':
                if (y < size - 1 && map[x][y + 1] != 3 && map[x][y + 1] != 4 && playernumber == 1) {
                    map[x][y] = 4;
                    this.y++;
                    map[x][y] = 1;
                } else if (y2 < size - 1 && map[x2][y2 + 1] != 3 && map[x2][y2 + 1] != 4 && playernumber == 0) {
                    map[x2][y2] = 3;
                    this.y2++;
                    map[x2][y2] = 2;
                } else {
                    end = true;
                    if (playernumber == 1){ looser = 0;}
                    if (playernumber == 0){ looser = 1;}
                }
            break;
        }
    }
}
