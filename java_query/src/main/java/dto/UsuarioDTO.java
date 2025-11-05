package dto;


public class UsuarioDTO {
	private String username;
	private String email;
	private String senha;
	private String fotobase64;
	
 public String getUsername() {
	  return username;
 }
 
 public void setUsername(String username){
	 this.username = username;
 }
 
 
 public String getEmail() {
	  return email;
}

public void setEmail(String email) {
	 this.email = email;
}


public String getSenha() {
	  return senha;
}

public void setSenha(String senha) {
	 this.senha = senha;
}

public String getFoto(String fotobase64){
	return fotobase64;
}
public void setFotobase64(String fotobase64) {
	this.fotobase64 = fotobase64;
}
}
