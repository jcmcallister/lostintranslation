package cis423;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;

import com.googler.Translator;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

public class UOKeanGUI extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    private Translator translator;
    private ImageIcon icon;
    private JLabel iconLabel;
    
    private String input;
    private String translation;
    private String retranslation;
    private String[] langs = { "en", "ru", "es" };
    private SpinnerThread sThread;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    UOKeanGUI frame = new UOKeanGUI();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public UOKeanGUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        icon = new ImageIcon("src/img/spinner.gif");
        iconLabel = new JLabel(icon);
        iconLabel.setBounds(48, 200, 52, 52);
        contentPane.add(iconLabel);
        iconLabel.setVisible(false);
        
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
        
        JComboBox<String> langSelector = new JComboBox<>(langs);
        langSelector.setFont(new Font("Tahoma", Font.PLAIN, 18));
        langSelector.setBounds(42, 317, 77, 79);
        contentPane.add(langSelector);
        
        JButton translateDownButton = new JButton("Translate v");
        translateDownButton.setBounds(140, 198, 228, 63);
        contentPane.add(translateDownButton);
        
        JButton translateUpButton = new JButton("Translate ^");
        translateUpButton.setBounds(368, 198, 228, 63);
        contentPane.add(translateUpButton);
        
        iconGenerator();
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
        
        
        translateDownButton.addActionListener(new ActionListener()
                                              {
            public void actionPerformed(ActionEvent click)
            {
                iconLabel.setVisible(true);
                translator = new Translator(langSelector.getSelectedItem().toString());
                translator.setLanguage(langSelector.getSelectedItem().toString());
                input = inputTextArea.getText();
                new TranslationWorker(input,outputTextArea,iconLabel).execute();
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
    
    public void iconGenerator()
    {
        icon = new ImageIcon("src/img/spinner.gif");
        iconLabel = new JLabel(icon);
        iconLabel.setBounds(48, 200, 52, 52);
        contentPane.add(iconLabel);
        repaint();
        iconLabel.setVisible(false);
    }
    private class TranslationWorker extends SwingWorker <String, Void>
    {
    
        private String input;
        private JTextArea ta;
        private JLabel spin;
    
        public TranslationWorker(String input, JTextArea ta, JLabel spin)
        {
            this.input = input;
            this.ta = ta;
            this.spin = spin;
        }
    
        @Override
        public String doInBackground()
        {
            translation = translator.getTranslation(input);
            retranslation = translator.getTranslation(translation);
            return retranslation;
        }
    
        @Override
        protected void done()
        {
            try
            {
                ta.setText(get());
                spin.setVisible(false);
            }
            catch(Exception e)
            {
                //exception
            }
        
        }
    
    }
    
}



