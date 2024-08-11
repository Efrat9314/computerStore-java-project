package gui;

import controller.Backend_DAO_List;
import model.HardWareProduct;
import model.Product;
import model.ProductType;
import model.SoftWareProduct;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewProductForm {
    public JPanel panel_p;
    private JLabel label_name;
    private JTextField name_field;
    private JLabel label_description;
    private JTextField description_field;
    private JTextField price_field;
    private JLabel price_label;
    private JComboBox cmbType;
    private JButton btn_add_product;
    private JLabel lblOtherField;
    private JTextField text_other_field;
    private JSpinner spinner_price;
    private JSpinner spinner_other_field;
    public ManageCatalogForm manageCatalogForm;

    public AddNewProductForm(ManageCatalogForm catalogForm) {
        manageCatalogForm=catalogForm;
        DefaultComboBoxModel model=new DefaultComboBoxModel(ProductType.values());
        cmbType.setModel(model);

        cmbType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isInHardWareModel())
                    lblOtherField.setText("תקופת אחריות");
                else
                    lblOtherField.setText("מס' משתמשים");
            }
        });

        btn_add_product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product p=null;
                try{
                    if(isInHardWareModel())
                        p=new HardWareProduct(10,name_field.getText(), description_field.getText(),((Integer) spinner_price.getValue()).floatValue(),((Integer)spinner_other_field.getValue()).intValue());
                    else
                        p=new SoftWareProduct(10,name_field.getText(), description_field.getText(), ((Integer) spinner_price.getValue()).floatValue(),((Integer)spinner_other_field.getValue()).intValue());
                    Backend_DAO_List.GetInstance().AddProduct(p);
                    JOptionPane.showMessageDialog(null,"המוצר התווסף בהצלחה");
                }
                 catch (Exception ex) {
                    ex.printStackTrace();
                     JOptionPane.showMessageDialog(null, "Error Placing product", "Error",JOptionPane.ERROR_MESSAGE);
                }
                manageCatalogForm.RefreshProductsList();
            }
        });
    }


    private boolean isInHardWareModel(){
        return cmbType.getSelectedItem()==ProductType.HARDWARE;
    }
}
