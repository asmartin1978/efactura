package es.application.ms_springmvc.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturaDTO {

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
