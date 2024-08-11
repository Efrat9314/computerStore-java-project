package gui;

import controller.Backend_DAO_List;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class storeManagerGUIView {
    private JButton addCustomer;
    private JButton products;
    private JButton makeOrder;
    private JButton allOrders;
    public JPanel panel_p;

    public storeManagerGUIView() {

        products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ManageCatalogForm");
                frame.setContentPane(new ManageCatalogForm().panel_p);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewCustomerForm form=new AddNewCustomerForm();
                form.setVisible(true);
            }
        });

        makeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PlaceOrderForm");
                frame.setContentPane(new PlaceOrderForm().panel_p);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        allOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ViewPurchasesForm");
                frame.setContentPane(new ViewPurchasesForm().panel_p);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("storeManagerGUIView");
        frame.setContentPane(new storeManagerGUIView().panel_p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    Backend_DAO_List.GetInstance().loadDataFromFile();
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Backend_DAO_List.GetInstance().saveDataToFile();
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
