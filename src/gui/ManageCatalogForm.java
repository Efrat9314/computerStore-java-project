package gui;

import controller.Backend_DAO_List;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

public class ManageCatalogForm {
    private JButton addNewProduct;
    private JButton removeProduct;
    private JList listProducts;
    public JPanel panel_p;
    private DefaultListModel AllProductsListModel;

    public ManageCatalogForm() {
        AllProductsListModel=new DefaultListModel();
        RefreshProductsList();
        listProducts.setModel(AllProductsListModel);

        addNewProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("AddNewProductForm");
                frame.setContentPane(new AddNewProductForm(ManageCatalogForm.this).panel_p);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        removeProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                List selectedValuesList = listProducts.getSelectedValuesList();
                for (Object p :selectedValuesList) {
                    AllProductsListModel.removeElement(p);

                    Backend_DAO_List.GetInstance().RemoveProduct((Product) p);
                }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }

        });
    }

    public void RefreshProductsList() {
        AllProductsListModel.clear();
        try {
            HashSet<Product> allProducts = Backend_DAO_List.GetInstance().getAllProducts();
            for (Product product : allProducts) {
                AllProductsListModel.addElement(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
