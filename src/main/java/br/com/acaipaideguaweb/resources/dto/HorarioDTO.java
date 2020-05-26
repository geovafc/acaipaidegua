package br.com.acaipaideguaweb.resources.dto;

import java.io.Serializable;
import java.util.Objects;

import br.com.acaipaideguaweb.model.TipoHorario;


/**
 * A DTO for the HorarioFuncionamento entity.
 */
public class HorarioDTO implements Serializable {

    private Long id;

    private String diaInicio;

    private String diaFinal;

    private String abertura;

    private String fechamento;

    private TipoHorario tipo;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(String diaInicio) {
        this.diaInicio = diaInicio;
    }
    
    
    
    public String getDiaFinal() {
		return diaFinal;
	}

	public void setDiaFinal(String diaFinal) {
		this.diaFinal = diaFinal;
	}

	public String getAbertura() {
		return abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	public String getFechamento() {
		return fechamento;
	}

	public void setFechamento(String fechamento) {
		this.fechamento = fechamento;
	}

	public TipoHorario getTipo() {
		return tipo;
	}

	public void setTipo(TipoHorario tipo) {
		this.tipo = tipo;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HorarioDTO horarioFuncionamentoDTO = (HorarioDTO) o;

        if ( ! Objects.equals(id, horarioFuncionamentoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
