package com.manager.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.TypeMap;

import com.manager.model.Movimiento;
import com.manager.dto.MovimientoDTO;


@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
    	ModelMapper modelMapper = new ModelMapper();
    	
     // Configuración de mapeo personalizado para el campo 'cuenta'
    /*	modelMapper.typeMap(MovimientoDTO.class, Movimiento.class)
        .addMapping(MovimientoDTO::getId, (dest, value) -> 
        dest.getCuenta().setId((Long) value));
    	
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
*/
        return modelMapper;
    }
}