package es.application.ms_springmvc.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="Factura")
@Getter
@Setter
public class Factura {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String concepto;   
    private Long importe;
    private Date fechapresentacion;
    private Date fechaemision;
    private String numeroserie;
    private String numerofactura;
    private String nifemisor;
    private String nombreemisor;
    private String nifreceptor;
    private String nombrereceptor;
    private String numaries;
    private String numface;
    private String puntoentrada;
    private String numregistrocontable;
    private String estado;
    private String solanulacion;
    
}
