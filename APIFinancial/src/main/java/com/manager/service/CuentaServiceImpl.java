package com.manager.service;

import java.beans.PropertyDescriptor;

import org.apache.tomcat.util.bcel.Const;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dto.ClienteDTO;
import com.manager.dto.CuentaDTO;
import com.manager.dto.Response;
import com.manager.dto.ResponseCuenta;
import com.manager.integration.ClienteFeignClient;
import com.manager.model.Cuenta;
import com.manager.repository.ICuentaRep;
import com.manager.util.Constants;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private ClienteFeignClient clienteFeignClient;
    
    @Autowired
	private ICuentaRep cuentaRep;
	
	@Autowired
    private ModelMapper modelMapper;

    public CuentaDTO obtenerCuentaConCliente(Long cuentaId) {
		Cuenta cuenta = cuentaRep.findById(cuentaId).orElseThrow(() ->
		new IllegalArgumentException("Cuenta no encontrada con id: " + cuentaId));;
        
        ClienteDTO cliente = clienteFeignClient.obtenerClientePorId(cuenta.getClienteId());
        CuentaDTO cuentaConCliente = new CuentaDTO();
        modelMapper.map(cuentaConCliente, cuenta);
        cuentaConCliente.setClienteDTO(cliente);
        return cuentaConCliente;
    }

	@Override
	public Response crear(CuentaDTO cuentaDTO) {
		Cuenta cuenta = new Cuenta();
		modelMapper.map(cuentaDTO, cuenta);
		cuentaRep.save(cuenta);
		Response response = new Response(Constants.CREATE, Constants.CREATE_MSG);
		return response;
	}

	@Override
	public Response actualizar(CuentaDTO cuentaActualizadoDTO) {
		Cuenta cuenta = new Cuenta();
		if (cuentaActualizadoDTO.getId()!= null){
			cuenta = cuentaRep.findById(cuentaActualizadoDTO.getId())
			        .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con id: " + cuentaActualizadoDTO.getId()));
		}else if(cuentaActualizadoDTO.getIdCliente()!= null){
			cuenta = cuentaRep.findByClienteId(cuentaActualizadoDTO.getIdCliente())
					.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con identificacion: " + cuentaActualizadoDTO.getIdCliente()));
		}else if(cuentaActualizadoDTO.getCliente()!= null){
			ClienteDTO cliente = clienteFeignClient.obtenerClientePorNombre(cuentaActualizadoDTO.getCliente());
			cuenta = cuentaRep.findByClienteId(cliente.getId())
					.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con identificacion: " + cuentaActualizadoDTO.getCliente()));
		}else{
			Response response = new Response(Constants.NOT_FOUND, Constants.NOT_FOUND_MSG);
			return response;
		}
		modelMapper.map(cuentaActualizadoDTO, cuenta);
		cuentaRep.save(cuenta);
		Response response = new Response(Constants.OK, Constants.OK_MSG);
		return response;
	}

	@Override
	public Response actualizarParcial(CuentaDTO cuentaActualizadoDTO) {
		Cuenta cuenta = new Cuenta();
		if (cuentaActualizadoDTO.getId()!= null){
			cuenta = cuentaRep.findById(cuentaActualizadoDTO.getId())
			        .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con id: " + cuentaActualizadoDTO.getId()));
		}else if(cuentaActualizadoDTO.getIdCliente()!= null){
			cuenta = cuentaRep.findByClienteId(cuentaActualizadoDTO.getIdCliente())
					.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con identificacion: " + cuentaActualizadoDTO.getIdCliente()));
		}else if(cuentaActualizadoDTO.getCliente()!= null){
			ClienteDTO cliente = clienteFeignClient.obtenerClientePorNombre(cuentaActualizadoDTO.getCliente());
			cuenta = cuentaRep.findByClienteId(cliente.getId())
					.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con identificacion: " + cuentaActualizadoDTO.getCliente()));
		}else{
			Response response = new Response(Constants.NOT_FOUND, Constants.NOT_FOUND_MSG);
			return response;
		}
		actualizarCampos(cuentaActualizadoDTO, cuenta);
		cuentaRep.save(cuenta);
		Response response = new Response(Constants.OK, Constants.OK_MSG);
		return response;
	}

	@Override
	public Response actualizar(CuentaDTO cuentaActualizadoDTO, Long id) {
		Cuenta cuenta = new Cuenta();
		cuenta = cuentaRep.findById(id)
			        .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con id: " + cuentaActualizadoDTO.getId()));
		modelMapper.map(cuentaActualizadoDTO, cuenta);
		cuentaRep.save(cuenta);
		Response response = new Response(Constants.OK, Constants.OK_MSG);
		return response;
	}

	@Override
	public ResponseCuenta obtenerPorNumero(String nroCuenta) {
		Cuenta cuenta = cuentaRep.findByNroCuenta(nroCuenta)
			        .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con Nro: " + nroCuenta));
		CuentaDTO cuentaDTO = new CuentaDTO();
		modelMapper.map(cuenta, cuentaDTO);
		ResponseCuenta response = new ResponseCuenta();
		response.setCode(Constants.FOUND);
		response.setMessage(Constants.FOUND_MSG);
		response.setCuenta(cuentaDTO);
		return response;
	}

	@Override
	public ResponseCuenta obtenerCuentaConCliente(Integer clienteId) {
		Cuenta cuenta = cuentaRep.findByClienteId(clienteId)
		        .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada para el cliente id: " + clienteId));
		ClienteDTO cliente = clienteFeignClient.obtenerClientePorId(clienteId);
		CuentaDTO cuentaDTO = new CuentaDTO();
		cuentaDTO.setClienteDTO(cliente);
		cuentaDTO.setCliente(cliente.getNombre());
		modelMapper.map(cuenta, cuentaDTO);
		ResponseCuenta response = new ResponseCuenta();
		response.setCode(Constants.FOUND);
		response.setMessage(Constants.FOUND_MSG);
		response.setCuenta(cuentaDTO);
		return response;
	}

	@Override
	public Response eliminar(Long id) {
		Cuenta cuenta = cuentaRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrado con id: " + id));
		cuentaRep.delete(cuenta);
		Response response = new Response(Constants.DELETE, Constants.DELETE_MSG+" el registro id:"+id);
		return response;
	}

	@Override
	public ResponseCuenta obtenerTodas() {
		List<CuentaDTO> cuentas = cuentaRep.findAll().stream()
		        .map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
		        .collect(Collectors.toList());
		ResponseCuenta response = new ResponseCuenta();
		response.setCuentaList(cuentas);
		if(cuentas.isEmpty()) {
			response.setCode(Constants.NOT_FOUND);
			response.setMessage(Constants.NOT_FOUND);
		}else{
			response.setCode(Constants.FOUND);
			response.setMessage(Constants.FOUND_MSG);
		}
		return response;
	}
	
	public void actualizarCampos(CuentaDTO cuentaActualizadoDTO, Cuenta cuentaExistente) {
        BeanUtils.copyProperties(cuentaActualizadoDTO, cuentaExistente, getNullPropertyNames(cuentaActualizadoDTO));
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
}
