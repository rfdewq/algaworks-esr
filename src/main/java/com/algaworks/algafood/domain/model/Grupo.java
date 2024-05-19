package com.algaworks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	
	@JoinTable(
	name = "grupos_permissoes",
	joinColumns = @JoinColumn(name = "grupo_id"),
	inverseJoinColumns =  @JoinColumn(name = "permissao_id"))
	@ManyToMany
	private List<Permissao> permissoes = new ArrayList<>();
}
