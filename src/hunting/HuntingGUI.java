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


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class HuntingGUI {
    
    private JFrame frame;
    private BoardGUI boardGUI;
    private final int INITIAL_BOARD_SIZE = 3;
    
    public HuntingGUI(){
        
    frame = new JFrame("Hunting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  

        boardGUI= new BoardGUI(INITIAL_BOARD_SIZE);
        ///for frame with grid layout
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        JMenu newMenu = new JMenu("New");
        gameMenu.add(newMenu);
        
        int[] boardSizes = new int[]{3, 5, 7};
        for (int boardSize : boardSizes) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            newMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().remove(boardGUI.getBoardPanel());
                    boardGUI= new BoardGUI(boardSize);
                    frame.getContentPane().add(boardGUI.getBoardPanel(),BorderLayout.CENTER);
                    frame.pack();
                }
            });
        }

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    

}
}
    

