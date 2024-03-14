package com.persons.manager.service;


import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persons.manager.dto.ClienteDTO;
import com.persons.manager.dto.Response;
import com.persons.manager.dto.ResponseCliente;
import com.persons.manager.model.Cliente;
import com.persons.manager.repository.IClienteRep;
import com.persons.manager.util.Constants;

import jakarta.transaction.Transactional;



@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteRep clienteRep;
	
	@Autowired
    private ModelMapper modelMapper;

	@Override
	public Response crear(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		modelMapper.map(clienteDTO, cliente);
		clienteRep.save(cliente);
		Response response = new Response(Constants.CREATE, Constants.CREATE_MSG);
		return response;
	}

	@Override
	@Transactional
	public Response actualizar(ClienteDTO clienteActualizadoDTO) {
		Cliente cliente = new Cliente();
		if (clienteActualizadoDTO.getId()!= null){
			cliente = clienteRep.findById(clienteActualizadoDTO.getId())
			        .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + clienteActualizadoDTO.getId()));
		}else if(clienteActualizadoDTO.getIdentificacion()!= null){
			cliente = clienteRep.findByIdentificacion(clienteActualizadoDTO.getIdentificacion())
					.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con identificacion: " + clienteActualizadoDTO.getIdentificacion()));
		}else{
			cliente = clienteRep.findByNombre(clienteActualizadoDTO.getNombre())
					.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con nombre: " + clienteActualizadoDTO.getNombre()));
		}
		modelMapper.map(clienteActualizadoDTO, cliente);
		clienteRep.save(cliente);
		Response response = new Response(Constants.OK, Constants.OK_MSG);
		return response;
	}
	
	@Override
	@Transactional
	public Response actualizarParcial(ClienteDTO clienteActualizadoDTO) {
		Cliente cliente = new Cliente();
		if (clienteActualizadoDTO.getId()!= null){
			cliente = clienteRep.findById(clienteActualizadoDTO.getId())
			        .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + clienteActualizadoDTO.getId()));
		}else if(clienteActualizadoDTO.getIdentificacion()!= null){
			cliente = clienteRep.findByIdentificacion(clienteActualizadoDTO.getIdentificacion())
					.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con identificacion: " + clienteActualizadoDTO.getIdentificacion()));
		}else{
			cliente = clienteRep.findByNombre(clienteActualizadoDTO.getNombre())
					.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con nombre: " + clienteActualizadoDTO.getNombre()));
		}
		actualizarCampos(clienteActualizadoDTO, cliente);
		clienteRep.save(cliente);
		Response response = new Response(Constants.OK, Constants.OK_MSG);
		return response;
	}
	
	@Override
	@Transactional
	public Response actualizar(ClienteDTO clienteActualizadoDTO, Integer id) {
		Cliente cliente = clienteRep.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + id));
		modelMapper.map(clienteActualizadoDTO, cliente);
		clienteRep.save(cliente);
		Response response = new Response(Constants.OK, Constants.OK_MSG);
		return response;
	}

	@Override
	@Transactional
	public Response actualizarPorIdentificacion(String identificacion, ClienteDTO clienteActualizadoDTO) {
		Cliente cliente = clienteRep.findByIdentificacion(identificacion)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con identificación de persona: " + identificacion));

		modelMapper.map(clienteActualizadoDTO, cliente);
		clienteRep.save(cliente);
		Response response = new Response(Constants.OK, Constants.OK_MSG);
		return response;
	}
	
	@Override
	@Transactional
    public Response actualizarPorNombre(String nombre, ClienteDTO clienteActualizadoDTO) {
        Cliente cliente = clienteRep.findByNombre(nombre)
                                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con nombre: " + nombre));
        modelMapper.map(clienteActualizadoDTO, cliente);
        clienteRep.save(cliente);
        Response response = new Response(Constants.OK, Constants.OK_MSG);
		return response;
    }

	@Override
	@Transactional
	public Response eliminar(Integer clienteid) {
		Cliente cliente = clienteRep.findById(clienteid)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + clienteid));
		clienteRep.delete(cliente);
		Response response = new Response(Constants.DELETE, Constants.DELETE_MSG+" el registro id:"+clienteid);
		return response;
	}

	@Override
	public ResponseCliente getAll() {
		List<ClienteDTO> clientes = clienteRep.findAll().stream()
		        .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
		        .collect(Collectors.toList());
		ResponseCliente response = new ResponseCliente();
		response.setClienteList(clientes);
		if(clientes.size()>0) {
			response.setCode(Constants.FOUND);
			response.setMessage(Constants.FOUND_MSG);
		}else{
			response.setCode(Constants.NOT_FOUND);
			response.setMessage(Constants.NOT_FOUND_MSG);
		}
		return response;
	}

	@Override
	public ResponseCliente getClienteById(int clienteId) {
		Cliente cliente = clienteRep.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + clienteId));
		ClienteDTO clienteDTO = new ClienteDTO();
		modelMapper.map(cliente, clienteDTO);
		ResponseCliente response = new ResponseCliente();
		response.setClienteDTO(clienteDTO);
		response.setCode(Constants.FOUND);
		response.setMessage(Constants.FOUND_MSG);
		return response;
	}

	@Override
	public ResponseCliente getClienteByNombre(String nombre) {
		List<ClienteDTO> clientes = clienteRep.findByNombre(nombre).stream()
		        .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
		        .collect(Collectors.toList());
		ResponseCliente response = new ResponseCliente();
		response.setClienteList(clientes);
		if(clientes.size()>0) {
			response.setClienteDTO(clientes.get(0));
			response.setCode(Constants.FOUND);
			response.setMessage(Constants.FOUND_MSG);
		}else{
			response.setCode(Constants.NOT_FOUND);
			response.setMessage(Constants.NOT_FOUND);
		}
		return response;
	}
	
	
	
	
	
	
	public void actualizarCampos(ClienteDTO clienteActualizadoDTO, Cliente clienteExistente) {
        BeanUtils.copyProperties(clienteActualizadoDTO, clienteExistente, getNullPropertyNames(clienteActualizadoDTO));
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
