package es.application.ms_springmvc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.application.ms_springmvc.model.entity.Factura;

public interface FacturaRepository extends PagingAndSortingRepository<Factura, Long> {

	List<Factura> findByConcepto(String concepto);
	
	List<Factura> findByConceptoContainingAndNifemisorContainingAndNumariesContainingAndNumfaceContaining (String concepto,String nifemisor , String numaries, String numface);
	
	
	@Query("select f from Factura f where f.concepto like %?1% AND f.nifemisor like %?2% and f.numaries like %?3% and f.numface like %?4%")
	List<Factura> buscadorFactura(String concepto,String nifemisor , String numaries, String numface);
	
}
