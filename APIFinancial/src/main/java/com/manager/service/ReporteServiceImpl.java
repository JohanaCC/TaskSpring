package com.manager.service;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.manager.dto.ClienteDTO;
import com.manager.dto.ReporteDTO;
import com.manager.dto.ResponseReport;
import com.manager.integration.ClienteFeignClient;
import com.manager.model.Movimiento;
import com.manager.repository.IMovimientoRep;
import com.manager.util.Constants;

@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
    private IMovimientoRep movimienteRep;

    @Autowired
    private ClienteFeignClient clientServiceFeignClient;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ResponseReport getReport(String startDate, String endDate, int clientId) throws ParseException {

    	ResponseReport response = new ResponseReport();
        List<Movimiento> movementFiltered = movimienteRep.findByFechaBetween(sdf.parse(startDate), sdf.parse(endDate)).stream()
                .filter(movement -> movement.getCuenta().getClienteId()==clientId ).toList();

        List<ReporteDTO> reportList = new ArrayList<>();
        movementFiltered.stream().forEach(movement->{

        	 ClienteDTO cliente = clientServiceFeignClient.obtenerClientePorId(clientId).getClienteDTO();
             ReporteDTO reportDto = ReporteDTO.builder()
                    .movimientoFecha(movement.getFecha())
                    .cliente(cliente.getNombre())
                    .nroCuenta(movement.getCuenta().getNroCuenta())
                    .tipoCuenta(movement.getCuenta().getTipoCuenta())
                    .saldoInicial(movement.getSaldoAnterior())
                    .estado(movement.getEstado())
                    .valor(movement.getValor())
                    .saldo(movement.getSaldo())
                    .build();
            reportList.add(reportDto);
        });
        response.setCode(Constants.OK);
        response.setMessage(Constants.OK_MSG);
        response.setReportList(reportList);

        return response;
    }
}
