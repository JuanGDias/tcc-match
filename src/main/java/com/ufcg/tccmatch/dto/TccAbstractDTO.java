package com.ufcg.tccmatch.dto;

import com.ufcg.tccmatch.model.AreaEstudo;

public class TccAbstractDTO {

    protected String titulo;

    protected AreaEstudo areaDeConhecimento;

    protected String descricao;

    protected String tipo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public AreaEstudo getAreaDeConhecimento() {
        return areaDeConhecimento;
    }

    public void setAreaDeConhecimento(AreaEstudo areaDeConhecimento) {
        this.areaDeConhecimento = areaDeConhecimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    


   

    

    

    
}
