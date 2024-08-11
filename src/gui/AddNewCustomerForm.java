package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controller.Backend_DAO_List;
import model.*;

public class AddNewCustomerForm extends JFrame {
    private JButton jButtonOK;
    private JLabel  jLabelID;
    private JLabel  jLabelName;
    private JLabel  jLabelAddress;
    private JTextField    jTextFieldID;
    private JTextField   jTextFieldName;
    private JTextField   jTextFieldAddress;

    public AddNewCustomerForm(){
        //אתחול הרכיבים
        jButtonOK = new JButton("OK");
        jLabelID = new JLabel("ID:");
        jLabelName = new JLabel("Name:");
        jLabelAddress = new JLabel("Address:");
        jTextFieldID = new JTextField();
        jTextFieldName = new JTextField();
        jTextFieldAddress = new JTextField();
        //הוספת הרכיבים
        getContentPane().add(jLabelID);
        getContentPane().add(jTextFieldID);
        getContentPane().add(jLabelName);
        getContentPane().add(jTextFieldName);
        getContentPane().add(jLabelAddress);
        getContentPane().add(jTextFieldAddress);
        getContentPane().add(jButtonOK);
        //עיצוב
        getContentPane().setBackground(Color.orange);
        jButtonOK.setBackground(Color.GREEN);
        //גודל חלון
        this.setPreferredSize(new Dimension(400, 200));
        //פריסה
        getContentPane().setLayout(new GridLayout(0,2,10,10));
        //סגירת הטופס הנוכחי כברירת מחדל
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //סידור הרכיבים באופן פרופורצינלי
        this.pack();

        jTextFieldID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (Character.isAlphabetic(e.getKeyChar()))
                    e.consume(); // לבטל את הארוע
            }
        });

        jTextFieldName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (!Character.isAlphabetic(e.getKeyChar()))
                    e.consume(); // לבטל את הארוע
            }
        });
        jButtonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Customer c=new Customer();
                    if(jTextFieldID.getText().length()!=9)
                        throw new Exception("ת.ז. אינה תקינה");
                    c.setId(Long.parseLong(jTextFieldID.getText()));
                    if(jTextFieldName.getText().isEmpty())
                        throw new Exception("חובה להקיש שם");
                    c.setName(jTextFieldName.getText());
                    if(jTextFieldAddress.getText().isEmpty())
                        throw new Exception("חובה להקיש כתובת");
                    c.setAddres(jTextFieldAddress.getText());
                    Backend_DAO_List.GetInstance().AddCustomer(c);
                    //הדפסה
                    JOptionPane.showMessageDialog(AddNewCustomerForm.this,"הלקוח התווסף בהצלחה");
                    jTextFieldID.setText("");
                    jTextFieldName.setText("");
                    jTextFieldAddress.setText("");
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddNewCustomerForm.this,ex.getMessage());
                }
            }
        });
    }
}
