package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		var jpql = new StringBuilder();
	    jpql.append("from Restaurante where 0 = 0 ");
	    
	    var parametros = new HashMap<String, Object>();
	    
	    if (StringUtils.hasLength(nome)) {	    	
	    	jpql.append("and nome like :nome ");
	    	parametros.put("nome", "%" + nome + "%");
	    }
	    
	    if (taxaFreteInicial != null) {
	    	jpql.append("and taxaFrete >= :taxaInicial ");
	    	parametros.put("taxaInicial", taxaFreteInicial);
	    }
	    
	    if (taxaFreteFinal != null) {
	    	jpql.append("and taxaFrete <= :taxaFinal ");
	    	parametros.put("taxaFinal", taxaFreteFinal);
	    }
	    	   
	    TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
	    
	    parametros.forEach((chave, valor) -> query.setParameter(chave, valor));	
	    
	    return query.getResultList();
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		var comFreteGratis = new RestauranteComFreteGratisSpec();
		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
	}



}