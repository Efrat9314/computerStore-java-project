package gui;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderForm {
    private JComboBox cmb_customer;
    private JComboBox cmb_products;
    private JList list1;
    private JButton btn_calcPrice;
    private JButton btn_submit;
    private JLabel lbl_customer;
    private JLabel lbl_products;
    private JLabel lbl_price;
    private JButton btn_removeProducts;
    private JButton btn_addToList;
    public JPanel panel_p;
    DefaultListModel SelectedProductsListModel;
    DefaultComboBoxModel CustomerModel;
    DefaultComboBoxModel ProductModel;

    public PlaceOrderForm() {
        try {
            ProductModel= new DefaultComboBoxModel<>(Backend_DAO_List.GetInstance().getAllProducts().toArray());
            cmb_products.setModel(ProductModel);
            CustomerModel= new DefaultComboBoxModel<>(Backend_DAO_List.GetInstance().getAllCustomers().values().toArray());
            cmb_customer.setModel(CustomerModel);
            SelectedProductsListModel = new DefaultListModel();
            list1.setModel(SelectedProductsListModel);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
            btn_addToList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SelectedProductsListModel.addElement((Product)cmb_products.getSelectedItem());
                }
            });
            btn_removeProducts.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Object objProduct : list1.getSelectedValues()) {
                        SelectedProductsListModel.removeElement(objProduct);
                    }
                }
            });
            btn_submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        PurchaseOrder order=new PurchaseOrder((Customer)cmb_customer.getSelectedItem(),new ArrayList(Arrays.asList(SelectedProductsListModel.toArray())));
                        Backend_DAO_List.GetInstance().PlaceOrder(order);
                        JOptionPane.showMessageDialog(null,"ההזמנה התווספה בהצלחה");
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error Placing order", "Error",JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
            btn_calcPrice.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        Product [] products=new Product[SelectedProductsListModel.size()];
                        SelectedProductsListModel.copyInto(products);
                        float total=Backend_DAO_List.GetInstance().CalcProductsTotalCost(products);
                        lbl_price.setText(String.valueOf(total));
                    }
                    catch (Exception e) {
                        Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE,null, e);
                    }
                }
            });
    }
}
