package com.sysco.asoh.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class StockItem {

    @Id
    @GeneratedValue
    int id;

    String name;

    int stockCount;

    public StockItem() {
    }

    public StockItem(String name, int stockCount) {
        this.name = name;
        this.stockCount = stockCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStockCount() {
        return stockCount;
    }
}
