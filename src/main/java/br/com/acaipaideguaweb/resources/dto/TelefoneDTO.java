package br.com.acaipaideguaweb.resources.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.acaipaideguaweb.model.Operadora;

import java.util.Objects;


public class TelefoneDTO implements Serializable {

    private Long id;

    private String numero;

    private Operadora operadora;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
    
    public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TelefoneDTO celularDTO = (TelefoneDTO) o;

        if ( ! Objects.equals(id, celularDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CelularDTO{" +
            "id=" + id +
            ", numero='" + numero + "'" +
            ", operadora='" + operadora + "'" +
            '}';
    }
}
