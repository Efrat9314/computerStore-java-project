package model;

import controller.Backend_DAO_List;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Backend_DAO_List.GetInstance().getAllCustomers());
    }
}