package es.application.ms_springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.application.ms_springmvc.model.dto.FacturaDTO;
import es.application.ms_springmvc.service.FacturaService;

@Controller
public class MainController  {

	@Autowired
	FacturaService factura;
	
	@GetMapping("/home")
    public String home(Model model) {       
		return root(model);
    }
	
	@GetMapping("/")
    public String root(Model model) {
		model.addAttribute("facturasForm" , new FacturaDTO());
        return "home";
    }
	
	@GetMapping("/consultafacturas")
    public String consultafacturas( @ModelAttribute FacturaDTO facturasForm ,Model model) {
		model.addAttribute("facturasForm", facturasForm);
		model.addAttribute("facturas", factura.buscaFacturas(facturasForm));
        return "fragments/listafacturas";
    }
	
	
	@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
	
	
	@GetMapping("/usuario")
    public String usuario() {
        return "/usuario";
    }
		
	@GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
	
	
	@GetMapping(value="/getpdf/{factura}")
	public ResponseEntity<byte[]> getPDF(@PathVariable("factura") String factura, Model model) {

		//TODO: Consumir un servicio que consultara el RF para traerse la factura en PDF
	    byte[] contents = null;
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    String filename = "output.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
	    return response;
	}

}