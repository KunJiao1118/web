package com.jk.demo.sto;

import com.jk.demo.dao.Dao_entities.ShopBook;
import lombok.Data;

import java.util.List;

@Data
public class BookSTO {
    private Integer total;
    private Integer page;
    private Integer current;
    private List<ShopBook> records;
}
