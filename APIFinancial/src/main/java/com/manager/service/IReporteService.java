package com.manager.service;
import java.text.ParseException;
import java.util.List;

import com.manager.dto.ReporteDTO;
import com.manager.dto.ResponseReport;

public interface IReporteService {

	public ResponseReport getReport(String startDate, String endDate, int clientId) throws ParseException;

}