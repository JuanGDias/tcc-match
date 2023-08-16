package com.ufcg.tccmatch.dto;

public class AlunoDTO {
	
	private String nome;

    private String matricula;

    private String email;

    private String periodoPrevistoParaConclusao;

    private String username;

    private String password;

    public String getNome() {
    	return nome;
    }
    
    public String getMatricula() {
    	return matricula;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public String getPeriodoPrevistoParaConclusao() {
    	return periodoPrevistoParaConclusao;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
