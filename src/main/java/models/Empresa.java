package models;

import java.io.Serializable;
import java.util.Objects;




public class Empresa implements Serializable{
	private int codigo;
	private String nome;
	
	public Empresa() {
		
	}
	public Empresa(int codigo, String nome) {
		this.codigo = codigo;
		this.nome=nome;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nome, other.nome);
	}
	
	
	
	
}
