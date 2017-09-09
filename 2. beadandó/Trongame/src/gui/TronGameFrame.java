package gui;

import logic.logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.IOException;
import javax.imageio.ImageIO;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class TronGameFrame extends JFrame implements KeyListener {

    private logic logic;
    private final JPanel gamePanel;
    private ImageIcon playerImg1;
    private ImageIcon playerImg2;
    private Timer timer;
    private int timerTime = 500;
    private JButton gamebutton = null;

    public TronGameFrame(logic logic) {
        this.logic = logic;
        setTitle("Tron Bike Game");
        setSize(200, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        gamePanel = new JPanel();
        add(gamePanel, BorderLayout.CENTER);
        timer = new Timer(timerTime, initialize);
        
        BufferedImage bufferimg;
        BufferedImage bufferimg2;

        try {
            bufferimg = ImageIO.read(TronGameFrame.class.getResourceAsStream("bike2.png"));
            bufferimg2 = ImageIO.read(TronGameFrame.class.getResourceAsStream("bike1.png"));
            playerImg1 = new ImageIcon(bufferimg);
            playerImg2 = new ImageIcon(bufferimg2);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(gamePanel, ex.fillInStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        newGame();
    }

    private void newGame() {
        gamePanel.removeAll();
        gamePanel.setLayout(new GridLayout(logic.getSize(), logic.getSize()));
        logic.newGame();
        for (int i = 0; i < logic.getSize(); i++) {
            for (int j = 0; j < logic.getSize(); j++) {
                MapButton jButton = makeMapButton(j, i);
                jButton.addKeyListener(this);
                gamePanel.add(jButton);
                gamebutton = jButton;
            }
        }

        JMenuBar menuBar = new JMenuBar();
        JButton startButton = new JButton(initialize);
        JButton pause2 = new JButton(pause);

        JMenu menu = new JMenu("New Game");
        for (int i = 12; i <= 36; i += 12) {
            JMenuItem item = new JMenuItem(newGameaction);
            item.setName(i + "");
            item.setText(i + " * " + i);
            menu.add(item);
        }
        
        menuBar.add(menu);
        menuBar.add(startButton);
        menuBar.add(pause2);
        setJMenuBar(menuBar);
        pack();
    }
    
    //Initialize game (start button)
    AbstractAction initialize = new AbstractAction("Start") {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timer.start(); startGame(0); startGame(1);
            gamebutton.requestFocusInWindow();
        }
    };
    
    //Pause game (pause button)
    AbstractAction pause = new AbstractAction("Pause") {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timer.stop();
        }
    };
    
    //start new game with selected n*n size
    AbstractAction newGameaction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            logic = new logic(Integer.parseInt(((JMenuItem) e.getSource()).getName()));
            logic.newGame();
            newGame();
        }
    };

    AbstractAction setPlayer = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            logic.newGame();
            newGame();
        }
    };

    //@param i,j set Gameplace units
    private MapButton makeMapButton(int j, int i) {
        MapButton jButton = new MapButton(j, i);
        jButton.setPreferredSize(new Dimension(25, 25));
        coloring(i, j, jButton);
        return jButton;
    }

    // @param i,j @param button generate new map with bikes on default places
    private void coloring(int i, int j, MapButton button) {
        switch (logic.getMapValue(i, j)) {
            case 1: button.setIcon(playerImg1); break;
            case 2: button.setIcon(playerImg2); break;
            case 3: button.setBackground(Color.GREEN); button.setIcon(null); break;
            case 4: button.setBackground(Color.RED); button.setIcon(null); break;
        }
    }

    // refresh the map/colors
    private void refreshMap() {
        for (Component component : gamePanel.getComponents()) {
            MapButton mb = (MapButton) component;
            int x = mb.X;
            int y = mb.Y;
            coloring(y, x, mb);
        }
    }

    //make the cars moving in a constant direction, choose winners.
    private void startGame(int activeplayer) {
        if (!logic.isEnd()) {
            if (activeplayer == 0) makeMotion(0, logic.getNumber()); refreshMap();
            if (activeplayer == 1) makeMotion(1, logic.getNumber2()); refreshMap();
            timerTime += 1;
        } else {
            timer.stop();
            logic.setNumber(0);
            logic.setNumber2(0);
            if(logic.getLooser() == 0){
                JOptionPane.showMessageDialog(null, "Winner: Green player");
            }
            else if(logic.getLooser() == 1){
                JOptionPane.showMessageDialog(null, "Winner: Red player");
            }
        }
    }
    
    // @param distance,direction set constant moving
    private void makeMotion(int distance, int direction) {
        if(direction == 0) logic.moveDirection('u', distance);
        if(direction == 1) logic.moveDirection('d', distance);
        if(direction == 2) logic.moveDirection('l', distance);
        if(direction == 3) logic.moveDirection('r', distance);
    }
    
    //@param e -> monitoring pressed buttons
    @Override
    public void keyPressed(KeyEvent e){}
    
    @Override
    public void keyReleased(KeyEvent e){makeControl(e);}
    
    @Override
    public void keyTyped(KeyEvent e){}

    // @param e set tronbikes direction to actual keypress
    private void makeControl(KeyEvent e) {
        //green player
        if (e.getKeyCode() == 38) logic.setNumber(0);
        if (e.getKeyCode() == 40) logic.setNumber(1);
        if (e.getKeyCode() == 37) logic.setNumber(2);
        if (e.getKeyCode() == 39) logic.setNumber(3);
        //red player
        if (e.getKeyChar() == 'w') logic.setNumber2(0);
        if (e.getKeyChar() == 's') logic.setNumber2(1);
        if (e.getKeyChar() == 'a') logic.setNumber2(2);
        if (e.getKeyChar() == 'd') logic.setNumber2(3);
    }
}
