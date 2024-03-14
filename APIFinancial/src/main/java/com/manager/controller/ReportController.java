package com.manager.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.dto.ReporteDTO;
import com.manager.dto.Response;
import com.manager.dto.ResponseReport;
import com.manager.service.ReporteServiceImpl;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReporteServiceImpl reportService;

    @GetMapping
    public ResponseEntity<ResponseReport> getReport(@RequestParam(value = "startDate") String startDate,
                                     @RequestParam(value = "endDate") String endDate,
                                     @RequestParam(value = "clientId") Long clientId)
                                    		 throws ParseException {
        return reportService.getReport(startDate,endDate, clientId);
    }

}	
