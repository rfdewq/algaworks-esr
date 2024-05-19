package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends 

			CustomJpaRepository<Restaurante, Long>, 
			RestauranteRepositoryQueries, 
			JpaSpecificationExecutor<Restaurante>
{

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	@Query("from Restaurante r join fetch r.cozinha join fetch r.formasPagamento")
	public List<Restaurante> findAll();
}
