// Karan Kumar
//
// RK1LBI
//
// Assignment 2 Hunting
//
// 2018/11/21 06:48:30
//
// This solution was submitted and prepared by Karan Kumar, RK1LBI for the
// Assignment 2 Hunting assignment of the Practical software engineering I. course.
//
// I declare that this solution is my own work.
//
// I have not copied or used third party solutions.
//
// I have not passed my solution to my classmates, neither  made it public.
//
// Students’ regulation of Eötvös Loránd University (ELTE Regulations
// Vol. II. 74/C. § ) states that as long as a student presents another
// student’s work - or at least the significant part of it - as his/her own
// performance, it will count as a disciplinary fault. The most serious
// consequence of a disciplinary fault can be dismissal of the student from
// the University.



package hunting;



/**

 *

 * @author Karan Kumar

 

*/



import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;







public class BoardGUI{

    

    boolean  choose = false;               //true for field taken and false for not taken

    boolean turn = true;                   //true for player 1 and false for player 2

    boolean s = true;                      //true if game is On and false if game is OFF

    String field = "";

    JButton[][] buttons;

    int prevRow;

    int prevCol;

    private JPanel boardPanel;

    int cnt = 0;

    int size;

    int fug_row;

    int fug_col;

    

     public BoardGUI(int boardSize) {



        size = 4 * boardSize;

        boardPanel = new JPanel();

        boardPanel.setLayout(new GridLayout(boardSize, boardSize));

        JButton[][] button = new JButton[boardSize][boardSize];

        int pos = 1;

        

        /**

         * ***To set the buttons in array***

         */

        for (int i = 0; i < button.length; i++) {



            for (int j = 0; j < button[i].length; j++) {

                String player = "";

                

                //For Hunter

                if (i == 0 && j == 0 || i == 0 && j == button.length - 1 || i == button.length - 1 && j == 0 || i == button.length - 1 && j == button.length - 1) {



                    player = "H";

                    player = player + new Integer(pos).toString();

                    pos++;



                }

                 

                //For Fugitive

                if (i == button.length / 2 && j == button[i].length / 2) {

                    player = "F";

                    fug_row = i;   

                    fug_col = j;  

                }



                JButton g = new JButton(player); //button g



                g.setBackground(Color.YELLOW);



                button[i][j] = g; 

                button[i][j].addActionListener(new MoveActionListener(i, j));

            }

        }

       

   

        /**

         * ***To Put the buttons in grid***

        */

        for (int i = 0; i < button.length; i++) {

            for (int j = 0; j < button[i].length; j++) {

                boardPanel.add(button[i][j]);

            }

        }

        // to use outside of the class  

        buttons = button; 

    }



    class MoveActionListener implements ActionListener {



        private int x, y;



        /**

         * ***To check values in rows and columns

         */

        public MoveActionListener(int x, int y) {

            this.y = y;

            this.x = x;

        }



        @Override

        public void actionPerformed(ActionEvent a) {

            JButton click = (JButton) a.getSource(); 

            String select = click.getText();



            

            if (s == true) {

                if (choose == false) {

                    if (turn == true) {

                        if (select.equals("H1") || select.equals("H2") || select.equals("H3") || select.equals("H4")) {

                            ///to store prev value

                            field = click.getText();

                            prevRow = x;

                            prevCol = y;

                            click.setText("");

                            choose = true;

                        } else {

                            System.out.println("Please select the hunter!");

                        }

                    } else if (turn == false) {

                        if (click.getText() == "F") {

                            field = "F";

                            prevRow = x;

                            prevCol = y;

                            click.setText(""); //??

                            choose = true;

                        } else {

                            System.out.println("Please select the fugitive!");

                        }

                    }

                } else if (choose == true) {

                    if (turn == true) {

                        ///for no player field

                        if (click.getText() == "") {

                            if(prevRow!=x || prevCol!=y){

                                

                            if (prevRow + 1 == x || prevRow - 1 == x || prevRow == x) {

                                if (prevCol + 1 == y || prevCol - 1 == y || prevCol == y) {

                                    click.setText(field);  ///for replacing player

                                    turn = !turn;

                                    choose = false;

                                    field = "";

                                    cnt++;

                                }

                            } // If hunter selects the filed which is already taken

                            else {

                                System.out.println("Please select the correct place for hunter!");

                            }

                        }

                        } else if (select.equals("H1") || select.equals("H2") || select.equals("H3") || select.equals("H4")) {



                            buttons[prevRow][prevCol].setText(field);///replacing field to old place

                            field = click.getText();

                            click.setText("");

                            prevRow = x;

                            prevCol = y;



                        } //If hunter does not select the nearby row or coloumn

                        else {

                            System.out.println("Please select the correct place for hunter!");

                        }

                    } else if (turn == false) {

                        if (click.getText() == "") {

                            if(prevRow!=x || prevCol!=y){

                            if (prevRow + 1 == x || prevRow - 1 == x || prevRow == x) {

                                if (prevCol + 1 == y || prevCol - 1 == y || prevCol == y) {

                                    click.setText(field);

                                    fug_row = x;

                                    fug_col = y;

                                    turn = !turn;

                                    choose = false;



                                    field = "";

                                }

                            } //If fugitive selects the filed which is already taken

                            else {

                                System.out.println("Please select the correct place for fugitive!");

                            }

                        }

                        } //If hunter does not select the nearby row or coloumn

                        else {

                            System.out.println("Please select the correct place for fugitive!");

                        }

                    }



                    if (cnt == size) {

                        s = false;

                        refresh(s, 1);

                    }else if (fug_row == 0 && fug_col == 0) {

                        if (buttons[fug_row][fug_col + 1].getText() != "" && buttons[fug_row + 1][fug_col].getText() != "" && buttons[fug_row + 1][fug_col + 1].getText() != "") {

                            s = false;

                            refresh(s, 2);

                        }

                    }  

                    else if (fug_row == buttons.length - 1 && fug_col == buttons[0].length - 1) {

                        if (buttons[fug_row - 1][fug_col].getText() != "" && buttons[fug_row][fug_col - 1].getText() != "" && buttons[fug_row - 1][fug_col - 1].getText() != "") {

                            s = false;

                            refresh(s, 2);

                        }

                    } 

                    else if (fug_row == 0 && fug_col == buttons[0].length - 1) {

                        if (buttons[fug_row][fug_col - 1].getText() != "" && buttons[fug_row + 1][fug_col].getText() != "" && buttons[fug_row + 1][fug_col - 1].getText() != "") {

                            s = false;

                            refresh(s, 2);

                        } 

                                

                       

                    } else if (fug_row == buttons.length - 1 && fug_col == 0) {

                        if (buttons[fug_row - 1][fug_col].getText() != "" && buttons[fug_row][fug_col + 1].getText() != "" && buttons[fug_row - 1][fug_col + 1].getText() != "") {

                            s = false;

                            refresh(s, 2);

                        }

                    }



                }

            }



     

        }

    }



    public JPanel getBoardPanel() {

        return boardPanel;

    }



    public void refresh(boolean won, int i) {

        if (won == false) {

                       

              /**

              * ***If Fugitive Wins***

              */

            if (i == 1) {

                JOptionPane.showMessageDialog(boardPanel,  "Fugitive won!", "Congratulations!",

                        JOptionPane.PLAIN_MESSAGE);



                //HuntingGUI gui = new HuntingGUI();

            }

               /**

               * ***If Hunter Wins***

               */

            

            if (i == 2) {

                JOptionPane.showMessageDialog(boardPanel, "Hunter won!", "Congratulations!", 

                        JOptionPane.PLAIN_MESSAGE);



               // HuntingGUI gui = new HuntingGUI();

            }

        }

    }

    

}

