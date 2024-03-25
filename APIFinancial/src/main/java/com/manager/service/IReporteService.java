package com.manager.service;
import java.text.ParseException;
import com.manager.dto.ResponseReport;

public interface IReporteService {

	public ResponseReport getReport(String startDate, String endDate, int clientId) throws ParseException;

}