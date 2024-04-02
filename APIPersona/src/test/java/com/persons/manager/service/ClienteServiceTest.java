package com.persons.manager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.persons.manager.dto.ClienteDTO;
import com.persons.manager.dto.Response;
import com.persons.manager.repository.IClienteRep;
import com.persons.manager.util.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private IClienteRep clienteRep;

    @Mock
    private ModelMapper modelMapper;

    private ClienteDTO clienteDTO;
    @BeforeEach
    public void setUp() {
        clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Juan Perez");
        clienteDTO.setIdentificacion("123456789");
        
    }

    @Test
    public void crearClienteTest() {
        // When
        Response response = clienteService.crear(clienteDTO);

        // Then
        assertThat(response.getCodigo()).isEqualTo(Constants.CREATE);
        
    }
}
