package com.manager.service;
import java.text.ParseException;
import java.util.List;

import com.manager.dto.ReporteDTO;

public interface IReporteService {

   List<ReporteDTO> getReport(String startDate, String endDate, int clientId) throws ParseException;

}