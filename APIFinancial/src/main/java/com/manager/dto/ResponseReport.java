package com.manager.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseReport extends Response{

	private List<ReporteDTO> reportList;
}
