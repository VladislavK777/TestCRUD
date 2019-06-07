package com.vladislavk.testcrud.model_ex;

import com.vladislavk.testcrud.entity.Product;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-07
 */

@Data
@Component
public class ResultOfSearch {
    private static Logger logger = LoggerFactory.getLogger(ResultOfSearch.class);
    private List<Product> productList;
}
