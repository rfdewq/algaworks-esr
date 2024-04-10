package com.algaworks.algafood.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@Component
public class NotificadorEmail implements Notificador {

	public NotificadorEmail() {
		System.out.println("Notificador E-mail # PRODUÇÃO");

	}

	@Override
	public void notificar() {
	}

}
