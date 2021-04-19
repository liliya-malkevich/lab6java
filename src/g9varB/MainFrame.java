package g9varB;


    import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

    public class MainFrame extends JFrame {
        private static final int Height = 500;
        private static final int Width = 700;
        private JMenuItem pauseMenuItem;
        private JMenuItem resumeMenuItem;
        private JMenuItem eraseMenuItem;
        private int stateErase = 0;
        private Field field = new Field();

        public MainFrame() {
            super("Programming and stream synchronization");
            this.setSize(500, 700);
            Toolkit kit = Toolkit.getDefaultToolkit();
            this.setLocation((kit.getScreenSize().width - 700) / 2, (kit.getScreenSize().height - 500) / 2);
            this.setExtendedState(6);
            JMenuBar menuBar = new JMenuBar();
            this.setJMenuBar(menuBar);
            JMenu ballMenu = new JMenu("Balls");
            Action addActionBall = new AbstractAction("Add ball") {
                public void actionPerformed(ActionEvent e) {
                    MainFrame.this.field.addBall();
                    if (!MainFrame.this.pauseMenuItem.isEnabled() && !MainFrame.this.resumeMenuItem.isEnabled()) {
                        MainFrame.this.pauseMenuItem.setEnabled(true);
                    }

                }
            };
            menuBar.add(ballMenu);
            ballMenu.add(addActionBall);
            JMenu controlMenu = new JMenu("Controls");
            menuBar.add(controlMenu);
            Action pauseAction = new AbstractAction("Pause") {
                public void actionPerformed(ActionEvent e) {
                    MainFrame.this.field.pause();
                    MainFrame.this.pauseMenuItem.setEnabled(false);
                    MainFrame.this.resumeMenuItem.setEnabled(true);
                }
            };
            this.pauseMenuItem = controlMenu.add(pauseAction);
            this.pauseMenuItem.setEnabled(false);
            Action resumeAction = new AbstractAction("Resume") {
                public void actionPerformed(ActionEvent e) {
                    MainFrame.this.field.resume();
                    MainFrame.this.pauseMenuItem.setEnabled(true);
                    MainFrame.this.resumeMenuItem.setEnabled(false);
                }
            };
            this.resumeMenuItem = controlMenu.add(resumeAction);
            this.resumeMenuItem.setEnabled(false);
            Action eraseAction = new AbstractAction("Erase") {
                public void actionPerformed(ActionEvent e) {
                    JPanel p = new JPanel();
                    JTextField inputField = new JTextField(10);
                    p.add(inputField);
                    JOptionPane.showConfirmDialog((Component)null, p, "Type smth ", 2);
                    MainFrame.this.field.setStateErase(Integer.parseInt(inputField.getText()));
                }
            };
            this.eraseMenuItem = controlMenu.add(eraseAction);
            this.eraseMenuItem.setEnabled(true);
            this.getContentPane().add(this.field, "Center");
        }
    }


