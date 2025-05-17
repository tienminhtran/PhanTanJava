package dao;

import entity.Item;

import java.util.List;

public interface ItemsDaoImpl {

    public List<Item> listItems(String supplierName);
}
