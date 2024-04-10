package com.algaworks.algafood.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("tes")
@Component
public class NotificadorEmailMock implements Notificador {

	public NotificadorEmailMock() {
		System.out.println("Notificador E-mail # MOCK");
	}
	@Override
	public void notificar() {

	}

}
