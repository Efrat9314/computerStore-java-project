package controller;

import model.Customer;
import model.HardWareProduct;
import model.Product;
import model.PurchaseOrder;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Backend_DAO_List implements Backend{
    private Map<Long,Customer>_Customers;
    private Set<Product>_Products;
    private List<PurchaseOrder>_PurchaseOrders;
    private static Backend_DAO_List singleBack;

    public static Backend_DAO_List GetInstance(){
        if(Objects.isNull(singleBack))
            singleBack=new Backend_DAO_List();
        return  singleBack;
    }

    private Backend_DAO_List() {
        _Customers = new HashMap< >();
        _Products = new HashSet< >();
        _PurchaseOrders = new ArrayList< >();
        _Products.add(new HardWareProduct(1,"computer1","new",2800,2));
        _Products.add(new HardWareProduct(2,"computer2","second hand",1500,12));
    }

    /**
     *
      * @param c add customer to the list map by customerId
     * @throws Exception
     */
    @Override
    public void AddCustomer(Customer c) throws Exception {
        _Customers.put(c.getId(),c);
    }

    /**
     *
     * @param c add product to the list
     * @throws Exception
     */
    @Override
    public void AddProduct(Product c) throws Exception {
        _Products.add(c);
    }

       @Override
    public HashMap<Long, Customer> getAllCustomers() throws Exception {
        return (HashMap<Long, Customer>) _Customers;
    }

    @Override
    public HashSet<Product> getAllProducts() throws Exception {
        return (HashSet<Product>) _Products;
    }

    /**
     *
     * @param po add purchaseOrder to the list
     * @throws Exception
     */
    @Override
    public void PlaceOrder(PurchaseOrder po) throws Exception {
        _PurchaseOrders.add(po);
    }

    @Override
    public void RemoveProduct(Product c) throws Exception {
        Product product = _Products.stream().filter(x -> x.equals(c)).findAny().get();
        _Products.remove(product);
    }

    @Override
    public ArrayList<Product> getCustomersOrders(Customer c) throws Exception {

        return _PurchaseOrders.stream().filter(x-> x.getOrderingCustomer().equals(c)).map(x->x.getProductList()).collect(()->new ArrayList<Product>(),(summer,x)->summer.addAll(x),(x,j)->x.addAll(j));
    }
    @Override
    public float getTotalPriceToCustomer(Customer c) throws Exception {
        float total= 0;
        ArrayList<Product> lst=getCustomersOrders(c);
        for (Product p:lst){
            total+=p.getPrice();
        }
        return total;
    }
    @Override
    public float CalcProductsTotalCost(Product[] products) throws Exception {
        DoubleSummaryStatistics sum= Arrays.stream(products).collect(Collectors.summarizingDouble(x-> x.getPrice()));
        return (float) sum.getSum();
    }
    public void saveDataToFile() throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("data"));
        out.writeObject(_Customers);
        out.writeObject(_Products);
        out.writeObject(_PurchaseOrders);
        out.close();
    }
    public void loadDataFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("data"));
        _Customers= (Map<Long, Customer>) in.readObject();
        _Products= (Set<Product>) in.readObject();
        _PurchaseOrders= (List<PurchaseOrder>) in.readObject();
        in.close();
    }
}
