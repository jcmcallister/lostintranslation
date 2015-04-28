import java.awt.BorderLayout;
import java.awt.EventQueue;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JTable;
 
 
public class UOKeanGUI extends JFrame {
 
        private JPanel contentPane;
        private JTable table;
 
        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        UOKeanGUI frame = new UOKeanGUI();
                                        frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }
 
        /**
         * Create the frame.
         */
        public UOKeanGUI() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 900, 500);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(null);
               
                final JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setBounds(140, 38, 449, 134);
                contentPane.add(textArea);
               
                JLabel lblInput = new JLabel("Input:");
                lblInput.setFont(new Font("Tahoma", Font.PLAIN, 18));
                lblInput.setBounds(53, 62, 77, 79);
                contentPane.add(lblInput);
               
                final JTextArea textArea_1 = new JTextArea();
                textArea_1.setLineWrap(true);
                textArea_1.setBounds(140, 284, 449, 134);
                contentPane.add(textArea_1);
               
                JLabel lblOutput = new JLabel("Output:");
                lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 18));
                lblOutput.setBounds(42, 317, 77, 79);
                contentPane.add(lblOutput);
               
                JButton btnNewButton = new JButton("Translate");
                btnNewButton.setBounds(140, 198, 449, 63);
                contentPane.add(btnNewButton);
               
                btnNewButton.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent click)
                        {
                        	System.out.println("Requesting translation of <phrase> which is " + textArea.getText().length() + "characters long!");
                                //Send Input to Google Translate
                                //Display Output
                        }
                });
               
               
               
                table = new JTable();
                table.setBounds(614, 85, 248, 303);
                contentPane.add(table);
               
                JLabel lblMeaningfulInformation = new JLabel("Meaningful Information");
                lblMeaningfulInformation.setBounds(666, 60, 146, 14);
                contentPane.add(lblMeaningfulInformation);
        }
}