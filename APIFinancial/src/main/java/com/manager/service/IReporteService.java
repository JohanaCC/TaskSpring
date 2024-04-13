package com.manager.service;
import java.text.ParseException;
import com.manager.dto.ResponseReport;

public interface IReporteService {

	public ResponseReport getReporte(String startDate, String endDate, int clientId) throws ParseException;

}