package com.web.TestPsicologia.controladores;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Controller
public class InicioControlador {

	@GetMapping("/inicio")
	public String EstadisticasGetRequest(Model model) throws ClassNotFoundException, SQLException {

		return "index";
	}

}
