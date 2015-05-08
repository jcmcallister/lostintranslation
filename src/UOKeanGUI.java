package cis423;
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

import com.googler.Translator;
 
public class UOKeanGUI extends JFrame {
 
        private JPanel contentPane;
        private JTable table;
        private Translator translator;
                
        private String input;
        private String translation;
        private String retranslation;
        private String output;
        
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
               
                JTextArea inputTextArea = new JTextArea();
                inputTextArea.setLineWrap(true);
                inputTextArea.setBounds(140, 38, 449, 134);
                contentPane.add(inputTextArea);
               
                JLabel lblInput = new JLabel("Input:");
                lblInput.setFont(new Font("Tahoma", Font.PLAIN, 18));
                lblInput.setBounds(53, 62, 77, 79);
                contentPane.add(lblInput);
               
                JTextArea outputTextArea = new JTextArea();
                outputTextArea.setLineWrap(true);
                outputTextArea.setBounds(140, 284, 449, 134);
                contentPane.add(outputTextArea);
               
                JLabel lblOutput = new JLabel("Output:");
                lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 18));
                lblOutput.setBounds(42, 317, 77, 79);
                contentPane.add(lblOutput);
               
                JButton btnNewButton = new JButton("Translate");
                btnNewButton.setBounds(140, 198, 449, 63);
                contentPane.add(btnNewButton);
                /*
                //
                //Create a new translator for translating to russian
		Translator translator1 = new Translator("ru");
		
		//Alternatively could set language this way:
		translator1.setLanguage("ru");
		
		//Translate a String english => russian
		String input1 = "dog";
		String translation1 = translator1.getTranslation(input1);
		System.out.println(translation1);
		
		//Translator a String russian => english (again must set language to a new one)
		translation1 = translator1.getTranslation(translation1, "en");
		System.out.println(translation1);
		//Alternatively could set language this way:
		//translator.setLanguage("en");
		
		//Let's find the Yandex query results:
		//int query = translator.getQuery(translation);
		//System.out.println(translation + " has: " + query + " results on Yandex!");
                //
                */

                
                btnNewButton.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent click)
                        {
                                translator = new Translator("ru");
                                translator.setLanguage("ru");
                                input = inputTextArea.getText();
                                translation = translator.getTranslation(input);
                                retranslation = translator.getTranslation(translation, "en");
                                
                                System.out.println(input);
                                System.out.println(translation);
                                outputTextArea.setText(translation);
                                //int query = translator.getQuery(translation);
                                repaint();
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