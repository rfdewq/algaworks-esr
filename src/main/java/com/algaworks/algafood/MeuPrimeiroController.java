package com.algaworks.algafood;

import org.springframework.stereotype.Controller;

@Controller
public class MeuPrimeiroController {

	public String hello() {
		return "Olá, mundo";
	}
}
