import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//So that all these actions can be performed. hence "ActionListener"
public class TicTacToe implements ActionListener {

    // Variables etc
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    int chanceFlag = 0;
    boolean playerTurn;

    TicTacToe() {
        // App size when opened
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setTitle("TicTacToe");

        // Title text
        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 200, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("TicTacToe");
        textField.setOpaque(true);

        // Border
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        // Layout for the buttons
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        // Adding said buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        // Adding title and buttons to frame
        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        startGame();
    }

    // Memanggil X atau O
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (playerTurn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(128, 0, 0));
                        buttons[i].setText("X");
                        playerTurn = false;
                        textField.setText("O turn");
                        chanceFlag++;
                        check();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 128));
                        buttons[i].setText("O");
                        playerTurn = true;
                        textField.setText("X turn");
                        chanceFlag++;
                        check();
                    }
                }
            }
        }
    }

    // Starting the game
    public void startGame() {
        // So the app won't start immediately
        try {
            textField.setText("Loading.....");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int chance = random.nextInt(100);

        if (chance % 2 == 0) {
            playerTurn = true;
            textField.setText("X Turn");
        } else {
            playerTurn = false;
            textField.setText("O Turn");
        }

    }

    // Method to restart the game
    public void gameOver(String s) {
        chanceFlag = 0;
        Object[] option = { "Restart", "Exit" };
        int n = JOptionPane.showOptionDialog(frame, "Game Over\n" + s, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        if (n == 0) {
            frame.dispose();
            new TicTacToe();
        } else {
            frame.dispose();
        }
    }

    // Checking whether it's a win or draw
    public void check() {
        int i = 0;
        while (buttons[i].getText() != "") {
            if (i == buttons.length - 1) {
                draw();
                break;
            }
            i++;
        }
        // If X wins
        if ((buttons[0].getText() == "X") &&
                (buttons[1].getText() == "X") &&
                (buttons[2].getText() == "X")) {
            xWins(0, 1, 2);
            gameOver("X wins");
        } else if ((buttons[3].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[5].getText() == "X")) {
            xWins(3, 4, 5);
            gameOver("X wins");
        } else if ((buttons[6].getText() == "X") &&
                (buttons[7].getText() == "X") &&
                (buttons[8].getText() == "X")) {
            xWins(6, 7, 8);
            gameOver("X wins");
        } else if ((buttons[0].getText() == "X") &&
                (buttons[3].getText() == "X") &&
                (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
            gameOver("X wins");
        } else if ((buttons[1].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
            gameOver("X wins");
        } else if ((buttons[2].getText() == "X") &&
                (buttons[5].getText() == "X") &&
                (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
            gameOver("X wins");
        } else if ((buttons[0].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
            gameOver("X wins");
        } else if ((buttons[2].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[6].getText() == "X")) {
            xWins(2, 4, 6);
            gameOver("X wins");
        }

        // If O wins
        else if ((buttons[0].getText() == "O") &&
                (buttons[1].getText() == "O") &&
                (buttons[2].getText() == "O")) {
            oWins(0, 1, 2);
            gameOver("O wins");
        } else if ((buttons[3].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[5].getText() == "O")) {
            oWins(3, 4, 5);
            gameOver("O wins");
        } else if ((buttons[6].getText() == "O") &&
                (buttons[7].getText() == "O") &&
                (buttons[8].getText() == "O")) {
            oWins(6, 7, 8);
            gameOver("O wins");
        } else if ((buttons[0].getText() == "O") &&
                (buttons[3].getText() == "O") &&
                (buttons[6].getText() == "O")) {
            oWins(0, 3, 6);
            gameOver("O wins");
        } else if ((buttons[1].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[7].getText() == "O")) {
            oWins(1, 4, 7);
            gameOver("O wins");
        } else if ((buttons[2].getText() == "O") &&
                (buttons[5].getText() == "O") &&
                (buttons[8].getText() == "O")) {
            oWins(2, 5, 8);
            gameOver("O wins");
        } else if ((buttons[0].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[8].getText() == "O")) {
            oWins(0, 4, 8);
            gameOver("O wins");
        } else if ((buttons[2].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[6].getText() == "O")) {
            oWins(2, 4, 6);
            gameOver("O wins");

            // Draw
        } else if (chanceFlag == 9) {
            textField.setText("Draw");
            gameOver("Draw");
        }
    }

    public void draw() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }
    }

    // Displaying a certain color when X or O won
    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(new Color(25, 200, 0));
        buttons[b].setBackground(new Color(25, 200, 0));
        buttons[c].setBackground(new Color(25, 200, 0));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText("X wins");
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(new Color(25, 200, 0));
        buttons[b].setBackground(new Color(25, 200, 0));
        buttons[c].setBackground(new Color(25, 200, 0));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText("O wins");
    }
}