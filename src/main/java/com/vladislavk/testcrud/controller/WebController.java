package com.vladislavk.testcrud.controller;

import com.vladislavk.testcrud.model_ex.ResultOfSearch;
import com.vladislavk.testcrud.service.WriteToFileExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

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
    public String home(Model model, Principal principal) {
        model.addAttribute("user", principal.getName());
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return "home";
    }

    // Выгрузка в Excel
    @RequestMapping(value = "/export")
    public void export(HttpServletResponse response) {
        writeToFileExcel.downloadFileExcel(response, resultOfSearch.getProductList());
    }
}
