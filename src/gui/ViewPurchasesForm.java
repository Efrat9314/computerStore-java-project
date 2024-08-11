package gui;

import controller.Backend_DAO_List;
import model.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class ViewPurchasesForm {
    private JComboBox cmb_customers;
    private JList list_ordersOfCustomer;
    private JLabel lbl_total;
    private JLabel lbl_TotalPrice;
    public JPanel panel_p;
    DefaultComboBoxModel customersModel;
     DefaultListModel ordersModel=new DefaultListModel();

public ViewPurchasesForm(){

    try {
        customersModel=new DefaultComboBoxModel(Backend_DAO_List.GetInstance().getAllCustomers().values().toArray());
        cmb_customers.setModel(customersModel);
        list_ordersOfCustomer.setModel(ordersModel);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
    cmb_customers.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                ordersModel.clear();
                List<?> orders = Backend_DAO_List.GetInstance().getCustomersOrders((Customer) cmb_customers.getSelectedItem());
                for (Object order : orders) {
                    ordersModel.addElement(order);
                }
                lbl_TotalPrice.setText(String.valueOf(Backend_DAO_List.GetInstance().getTotalPriceToCustomer((Customer) cmb_customers.getSelectedItem())));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    });


}

}

