
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.sql.*;
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List; 

public class Panal extends javax.swing.JPanel {
    Connect data_base;
    Sudoku game;
    SaveLoad SvLd;
    private Timer timer;
    private JButton nbtn = new JButton("new game");
    private static JTextField[][] boxes;
    private JTextField user = new JTextField();
    private JLabel label = new JLabel("      Timer :00 : 00 : 00");
    private JLabel TextLabel = new JLabel("         Enter your Name :");
    private JPanel[][] paneles;
    private JPanel center, bPanel, levelPanel;
    private JButton nBtn, cBtn, eBtn, hardBtn, midBtn, easyBtn, slove, about;
    private int[][] temp = new int[9][9];
    private int[][] grid = new int[9][9];
    private int counter = 0;
    private int score= 0;

    public JTextField newtextfield() {
        JTextField j = new JTextField("");
        j.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        j.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        j.setHorizontalAlignment(JTextField.CENTER);
        /*-------------------mouse lisner----------------*/
        j.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.decode("#f6ea80")));
                    ((JTextField) e.getSource()).setBackground(Color.decode("#f6ea80"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.lightGray));
                    ((JTextField) e.getSource()).setBackground(Color.white);
                }
            }
        });
        /*------------------------------------------------*/

        j.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setForeground(Color.decode("#0c4"));
                } else {
                    ((JTextField) e.getSource()).setForeground(Color.black);
                }
            }
        });
        return j;
    }
    
    public Panal() {
        initComponents();
        /*------------------------main panal  -------------------------------------*/
        center = new JPanel();  //main panel
        center.setLayout(new GridLayout(3, 3));     //grid for 3*3 
        center.setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(center);  //add main panel to frame 

        boxes = new JTextField[9][9];
        paneles = new JPanel[3][3];
        TextLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        TextLabel.setForeground(Color.black);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                paneles[i][j] = new JPanel();
                paneles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                paneles[i][j].setLayout(new GridLayout(3, 3));
                center.add(paneles[i][j]);
            }
        }
        /*------------------------text fildes in boxes-------------------------------------*/

        for (int n = 0; n < 9; n++) {
            for (int i = 0; i < 9; i++) {
                boxes[n][i] = newtextfield();
                int fm = (n + 1) / 3;
                if ((n + 1) % 3 > 0) {
                    fm++;
                }
                int cm = (i + 1) / 3;
                if ((i + 1) % 3 > 0) {
                    cm++;
                }
                paneles[fm - 1][cm - 1].add(boxes[n][i]);   //add box to panel 
            }
        }
        /*------------------------panal for buttons -------------------------------------*/

        bPanel = new JPanel();
        bPanel.setBackground(Color.decode("#AABFFF"));
        bPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        bPanel.setLayout(new GridLayout(4, 3, 0, 20));

        /*------------------------panal for new game button -------------------------------------*/
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                label.setText(TimeFormat(counter));
                counter++;

            }
        };

        /*------------------------panal for load game button -------------------------------------*/
        nBtn = new JButton("Load Game");
        nbtn.setSize(20, 50);
        timer = new Timer(1000, action);
        nBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                counter = 0;
                timer.start();
                restgame();
                int[][] temp1=SaveLoad.Load();
                int[][] grid1=GridList.Load();
                                                        
                setarray(grid1,temp1);
                setTextLable();
            }
        });

        /*------------------------panal for check game button -------------------------------------*/
        cBtn = new JButton("Check Game +30s");

        cBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!boxes[i][j].isEditable()) {
                            continue;
                        } else if (boxes[i][j].getText().equals(String.valueOf(grid[i][j]))) {
                            boxes[i][j].setBackground(Color.decode("#C0DCD9"));
                        } else if (boxes[i][j].getText().isEmpty()) {
                            boxes[i][j].setBackground(Color.WHITE);
                            continue;
                        } else {
                            boxes[i][j].setBackground(Color.red);
                        }
                    }
                }
                 counter += 30;
            }
        });
        /*------------------------panal for new Exit button -------------------------------------*/
        eBtn = new JButton("Exit");

        eBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*------------------------panal for new Hard button -------------------------------------*/
        easyBtn = new JButton("Hard");

        easyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                Sudoku.setlevel(4);
                Sudoku.newGame();
            }
        });
        /*------------------------panal for new Hard button -------------------------------------*/
        midBtn = new JButton("Medium");

        midBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                Sudoku.setlevel(3);
                Sudoku.newGame();

            }
        });
        /*------------------------panal for new Hard button -------------------------------------*/
        hardBtn = new JButton("Easy");

        hardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                Sudoku.setlevel(2);
                Sudoku.newGame();
            }
        });
        /*------------------------panal for new Hard button -------------------------------------*/
        slove = new JButton("Submit");

        slove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int checker=submitgame();

                    if(checker==1)
                    {
                        timer.stop();
                        score=100-(counter/5);
                     if(score<0)
                      {
                         score = 0;
                       }
                    String name = user.getText();
                    Connect.connect();
                    Connect.createNewTable();

                    Connect.insert(name, score);
                    JOptionPane.showMessageDialog(center, "Your score is : "+ score);
                    String sa[]=Connect.MaxSelect(name); 
                    JOptionPane.showMessageDialog(center, "Your Personal Best is : "+"\n"+ sa[0]+"\n"+sa[1]); 

                    }
                    
                    
                    else if(checker==2)
                    {
                    SaveLoad.connect();
                    GridList.connect();
                    SaveLoad.createNewTable();
                    GridList.createNewTable();
                    savetime();
                   
                    JOptionPane.showMessageDialog(center, "Saved");                    
                    }
                    
                    else{
                        JOptionPane.showMessageDialog(center, "Wrong");
                        for (int i = 0; i < 9; i++) {
                          for (int j = 0; j < 9; j++) {
                            boxes[i][j].setText(String.valueOf(grid[i][j]));
                        }
                    }                    
                    }
                       counter = 0;
                    label.setText(TimeFormat(counter
                    ));

            
}
        });
        /*------------------------panal for new about button -------------------------------------*/
        about = new JButton("HighScore");

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Connect.connect();
                    String sa[]=Connect.HighScore(); 
                    for (int i=0; i<sa.length; i=i+2)  
                    {  
                    JOptionPane.showMessageDialog(center, "Top 5 Highscores: \n \n "+(1+(i/2))+"\n"+ sa[i]+"\n"+sa[i+1]);
                    }  
            }
        });
        /*------------------------panal for new about button -------------------------------------*/
         user.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) { 
                    ((JTextField) e.getSource()).setText("");
            }
        });

        /*------------------------add button panal and butons to frame and panel -------------------------------------*/
        bPanel.add(hardBtn);   //add new game button to 
        bPanel.add(midBtn);
        bPanel.add(easyBtn);
        bPanel.add(nBtn);   //add new game button to 
        bPanel.add(cBtn);
        bPanel.add(eBtn);
        bPanel.add(TextLabel);
        bPanel.add(user);
        bPanel.add(slove);
        bPanel.add(label);
        bPanel.add(about);

        add(bPanel, "South");   //add button panel to frame 

    }

    public void setarray(int[][] grid, int[][] temp) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.temp[i][j] = temp[i][j];
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public void setTextLable() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.temp[i][j] != 0) {
                    boxes[i][j].setText(String.valueOf(this.temp[i][j]));
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.decode("#C0DCC0"));
                } else {
                    boxes[i][j].setText("");
                }
            }
        }
    }

    public static void restgame() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boxes[i][j].setForeground(Color.black);
                boxes[i][j].setEditable(true);
                boxes[i][j].setBackground(Color.WHITE);
            }
        }
    }
    
    public int submitgame(){
                    int submit=1;
                    for (int i = 0; i < 9; i++) {
                     for (int j = 0; j < 9; j++) {
                        if (!boxes[i][j].isEditable()) {
                            continue;
                        } else if (boxes[i][j].getText().equals(String.valueOf(grid[i][j]))) {
                            continue;
                        } else if (boxes[i][j].getText().isEmpty()) {
                            submit=2;
                        } else {
                            submit=0;
                        }
                    }
                }
                        return submit;
    }
    
    public void savetime(){
                        for (int i = 0; i < 9; i++) {
                            for(int j=0;j<9;j++){
                              if(!boxes[i][j].getText().equals(String.valueOf(grid[i][j]))){
                                boxes[i][j].setText("0");
                                }
                                    }
                            GridList.insert(i, grid[i][0], grid[i][1], grid[i][2], grid[i][3], grid[i][4], grid[i][5], grid[i][6], grid[i][7], grid[i][8]);                            
                            SaveLoad.insert(i, Integer.parseInt(boxes[i][0].getText()), Integer.parseInt(boxes[i][1].getText()), Integer.parseInt(boxes[i][2].getText()),
                                            Integer.parseInt(boxes[i][3].getText()), Integer.parseInt(boxes[i][4].getText()), Integer.parseInt(boxes[i][5].getText()), Integer.parseInt(boxes[i][6].getText()), Integer.parseInt(boxes[i][7].getText()), Integer.parseInt(boxes[i][8].getText()));
                        }}
    
    
    private String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count - hours * 3600) / 60;
        int seconds = count - minutes * 60;

        return String.format("      Timer :" + "%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
