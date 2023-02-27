package com.sysco.asoh.controller;

import com.sysco.asoh.model.StockItem;
import com.sysco.asoh.model.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@RestController
public class ApiController {

    @Autowired
    private StockItemRepository _stockItemRepository;

    @Autowired
    JdbcTemplate _jdbcTemplate;

    @RequestMapping("/db")
    public String dbConnection() throws SQLException {
        Connection connection = Objects.requireNonNull(_jdbcTemplate.getDataSource()).getConnection();
        return connection.getMetaData().getURL();
    }

    @RequestMapping("/stock")
    public Iterable<StockItem> allStock() {
        return _stockItemRepository.findAll();
    }

    @RequestMapping("/stock/{id}")
    public StockItem findStockById(@PathVariable Integer id) {
        return _stockItemRepository.findById(id).
                orElse(new StockItem("Not Found", 0));
    }

    @Bean
    CommandLineRunner initDB() {
        return args -> {
            _stockItemRepository.save(new StockItem("Corn", 5));
            _stockItemRepository.save(new StockItem("Potato", 0));
            _stockItemRepository.save(new StockItem("Banana", 12));
            _stockItemRepository.save(new StockItem("Apple", 8));
        };
    }

}
