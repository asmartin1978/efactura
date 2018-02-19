package es.application.ms_springmvc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.application.ms_springmvc.model.dto.FacturaDTO;
import es.application.ms_springmvc.model.entity.Factura;
import es.application.ms_springmvc.model.repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	FacturaRepository repository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public List<FacturaDTO> getAllFacturas() {
		List<Factura> lista =  (List<Factura>) repository.findAll();
		List<FacturaDTO> listaDto = lista.stream()
				.map(factdto -> convertToDto(factdto))
				.collect(Collectors.toList());
		
		return listaDto;			
	}
	
	private FacturaDTO convertToDto(Factura f) {
		FacturaDTO facturaDto = modelMapper.map(f, FacturaDTO.class);
	    return facturaDto;
	}
		
}
