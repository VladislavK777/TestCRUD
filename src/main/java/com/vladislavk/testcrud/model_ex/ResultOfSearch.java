package com.vladislavk.testcrud.model_ex;

import com.vladislavk.testcrud.entity.Product;
import lombok.Data;
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
    private List<Product> productList;
}
