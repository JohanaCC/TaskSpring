package com.manager.service;

import java.beans.PropertyDescriptor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dto.MovimientoDTO;
import com.manager.dto.Response;
import com.manager.dto.ResponseMovimiento;
import com.manager.model.Cuenta;
import com.manager.model.Movimiento;
import com.manager.repository.ICuentaRep;
import com.manager.repository.IMovimientoRep;
import com.manager.util.Constants;

import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

    
    @Autowired
	private IMovimientoRep movimientoRep;
    @Autowired
	private ICuentaRep cuentaRep;
	
	@Autowired
    private ModelMapper modelMapper;

	@Override
	public Response crear(MovimientoDTO movimientoDTO) {
		Response response = new Response();
		Movimiento movimiento = new Movimiento();
			
		MovimientoDTO movimientValido = controlarSaldo(movimientoDTO);
		
		if(movimientValido.getEstado()) {
			Cuenta cuenta = cuentaRep.findById(movimientoDTO.getCuenta().getId()).get();
			modelMapper.map(movimientoDTO, movimiento);
			movimiento.setCuenta(cuenta);
			movimientoRep.save(movimiento);
			response = new Response(Constants.CREATE, Constants.CREATE_MSG);
		}else {
			response = new Response(Constants.NOT_BALANCE, Constants.NOT_BALANCE_MSG);
		}
		
		return response;
	}
	
	@Transactional
	@Override
	public Response actualizarUltimoMovimiento(MovimientoDTO movimientoActualizadoDTO, int tipo) {
		Response response = new Response();
    	Movimiento ultimoMovimiento = new Movimiento();
		if (movimientoActualizadoDTO.getCuenta().getId()!= null){
			ultimoMovimiento = movimientoRep
					.findLastMovimientoByCuentaId(movimientoActualizadoDTO.getCuenta().getId())
					.orElseThrow(() -> new IllegalArgumentException("Debe indicar el ID de la CUENTA a modificar"));
			if ((movimientoActualizadoDTO.getId()!=null && ultimoMovimiento.getId()!= movimientoActualizadoDTO.getId())
					|| movimientoActualizadoDTO.getFecha().before(ultimoMovimiento.getFecha())) {
				return response = new Response(Constants.NOT_ALLOWED, Constants.NOT_ALLOWED_MSG);
			}else if(ultimoMovimiento!=null) {
				if (movimientoActualizadoDTO.getEstado()) {
					movimientoActualizadoDTO = controlarSaldo(movimientoActualizadoDTO);
					if(movimientoActualizadoDTO.getEstado()) {
						response = new Response(Constants.OK, Constants.OK_MSG);
						
					}else {
						response = new Response(Constants.NOT_BALANCE, Constants.NOT_BALANCE_MSG);
					}
				}
			}
		}else 
			return response = new Response(Constants.NOT_FOUND, Constants.NOT_FOUND_MSG);

		if(ultimoMovimiento.getEstado()) {
			if(tipo == Constants.UPDATE_PUT){//PUT
				modelMapper.map(movimientoActualizadoDTO, ultimoMovimiento);
			}else { //PATCH
				actualizarCampos(movimientoActualizadoDTO, ultimoMovimiento);}
			movimientoRep.save(ultimoMovimiento);
		}
		return response;
	}
	

	@Transactional
	@Override
	public Response eliminar(Long id) {
		Movimiento movimiento = movimientoRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento no encontrado con id: " + id));
		movimientoRep.delete(movimiento);
		Response response = new Response(Constants.DELETE, Constants.DELETE_MSG+" el registro id:"+id);
		return response;
	}

	@Override
	public ResponseMovimiento getAll() {
		List<MovimientoDTO> movimientos = movimientoRep.findAll().stream()
		        .map(movimiento -> modelMapper.map(movimiento, MovimientoDTO.class))
		        .collect(Collectors.toList());
		ResponseMovimiento response = new ResponseMovimiento();
		response.setMovimientoList(movimientos);
		if(movimientos.isEmpty()) {
			response.setCodigo(Constants.NOT_FOUND);
			response.setMensaje(Constants.NOT_FOUND_MSG);
		}else{
			response.setCodigo(Constants.FOUND);
			response.setMensaje(Constants.FOUND_MSG);
		}
		return response;
	}
	
	@Override
	public ResponseMovimiento getByCuenta(Long idCuenta) {
		List<MovimientoDTO> movimientos = movimientoRep.findByCuentaId(idCuenta).stream()
		        .map(movimiento -> modelMapper.map(movimiento, MovimientoDTO.class))
		        .collect(Collectors.toList());
		ResponseMovimiento response = new ResponseMovimiento();
		response.setMovimientoList(movimientos);
		if(movimientos.isEmpty()) {
			response.setCodigo(Constants.NOT_FOUND);
			response.setMensaje(Constants.NOT_FOUND_MSG);
		}else{
			response.setCodigo(Constants.FOUND);
			response.setMensaje(Constants.FOUND_MSG);
		}
		return response;
	}
	
	public void actualizarCampos(MovimientoDTO movimientoActualizadoDTO, Movimiento movimientoExistente) {
        BeanUtils.copyProperties(movimientoActualizadoDTO, movimientoExistente, getNullPropertyNames(movimientoActualizadoDTO));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
    
    private MovimientoDTO controlarSaldo(MovimientoDTO movimientoDTO) {	
    	Long idCuenta = movimientoDTO.getCuenta().getId();
    	if (idCuenta == null) {
    	    throw new IllegalArgumentException("Debe indicar el ID de la CUENTA a modificar");
    	}

    	Movimiento ultimoMovimiento = movimientoRep
    	        .findLastMovimientoByCuentaId(idCuenta)
    	        .orElse(null);
    	
		Double nuevoSaldo = movimientoDTO.getValor() ;
		Date fechaUltimoMovimiento = movimientoDTO.getFecha();
		Double saldoInicial = 0.0 ;
    	if(ultimoMovimiento!=null) {
			nuevoSaldo = ultimoMovimiento.getSaldo() + movimientoDTO.getValor();	
			fechaUltimoMovimiento = ultimoMovimiento.getFecha();
			saldoInicial = ultimoMovimiento.getSaldo();
		}
    	
    	//Controla saldo y fecha de movimiento
    	if(nuevoSaldo <0 || fechaUltimoMovimiento.after(movimientoDTO.getFecha())) {
			movimientoDTO.setEstado(false);
		}else {
			movimientoDTO.setSaldo(nuevoSaldo);
			movimientoDTO.setSaldoAnterior(saldoInicial);
			movimientoDTO.setEstado(true);
		}
    	return movimientoDTO;
    }    
   
}
