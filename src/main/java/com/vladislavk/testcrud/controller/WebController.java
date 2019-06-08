package com.vladislavk.testcrud.controller;

import com.vladislavk.testcrud.model_ex.ResultOfSearch;
import com.vladislavk.testcrud.service.WriteToFileExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-07
 */

@Controller
public class WebController {
    private static Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private ResultOfSearch resultOfSearch;
    @Autowired
    private WriteToFileExcel writeToFileExcel;

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    // Выгрузка в Excel
    @RequestMapping(value = "/export")
    public void export(HttpServletResponse response) {
        writeToFileExcel.downloadFileExcel(response, resultOfSearch.getProductList());
    }
}
