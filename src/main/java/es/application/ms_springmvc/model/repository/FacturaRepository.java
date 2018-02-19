package es.application.ms_springmvc.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import es.application.ms_springmvc.model.entity.Factura;

public interface FacturaRepository extends PagingAndSortingRepository<Factura, Long> {

	List<Factura> findByConcepto(String concepto);
}
