package lv.venta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilsController {

	@GetMapping("/my_access_denied") //nostrādās kad būs Forbiden jeb 403 kods
	public String accessDeniedGetFunc() {
		return "access-denied-page.html";
	}
	
	
	
	
	
	
	
	
	
	
}
