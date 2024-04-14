package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade salvar(Cidade cidade) {

		Long estadoId = cidade.getEstado().getId();

		Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O Estado informado pelo id %d não existe.", estadoId)));

		cidade.setEstado(estado);
		
		return cidade;
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException ex) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com o id %d", cidadeId));

		} catch (DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(
					String.format("A cidade informada pelo id %d não pode ser excluída.", cidadeId));
		}
	}
}
