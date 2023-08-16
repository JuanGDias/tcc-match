package com.ufcg.tccmatch.dto;

import java.util.List;

public class ProfessorDTO {
	
	private String nome;

    private String email;

    private List<String> laboratorio;

    private String username;

    private String password;
    
    public String getNome() {
    	return this.nome;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public List<String> getLaboratorio() {
    	return this.laboratorio;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
