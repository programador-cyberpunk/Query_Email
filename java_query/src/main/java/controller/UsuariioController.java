package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UsuarioDTO;

import java.io.BufferedReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.google.gson.Gson;
import java.io.PrintWriter;

 @WebServlet("/usuarios")
 public class UsuariioController extends HttpServlet{
  private static final long serialVersionUID = 1L; //procurei, mas eu nao tenho ideia de que porra seja isso
   private static final Map<String, UsuarioDTO> bancoDeDados = new ConcurrentHashMap<>();
 
	  private Gson gson = new Gson();
	  @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response){
		  
		  throws ServletException, IOException{
			  StringBuilder sb = new StringBuilder();
			  String line;
			   while((line = reader.readline() != null)) {
				   sb.append(line);
			   }
			String jsonPayload = sb.toString();
			UsuarioDTO novoUsuario = gson.fromJson(jsonPayload, UsuarioDTO.class);
			
			 if(novoUsuario == null || novoUsuario.getUsername() == null){
				 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Dados invalidos nessa porra, corrige ai");
				return;
			 }
			if(bancoDeDados.containsKey(novoUsuario.getUsername())) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				response.getWriter().write("ERRO: Ja tem um peao usano esse nick");
			}else {
				bancoDeDados.put(novoUsuario.getUsername(), novoUsuario);
				System.out.println("Usuario foi salvo, gg: " + novoUsuario.getUsername());
			}
		  }
	  }
	  
	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException{
		  String username = request.getParameter("username");
		  
		  UsuarioDTO usuarioEncontrado = new UsuarioDTO();
		  usuarioEncontrado.setUsername(username);
		   if(usuarioEncontrado !=null) {
			   usuarioEncontrado.setSenha(null);
			Gson gson = new Gson();
			String usuarioJson = gson.toJson(usuarioEncontrado);
			response.setContentType("application/jsonn");
			response.setCharacterEncoding("UTF-8");
			
			//essa porra agora escreve o Json na resposta
			PrintWriter out = response.getWriter();
			out.print(usuarioJson);
			out.flush(); //que porra e flush??
			
			}else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().write("Usuario nao encontrado");
			}
		  
		  System.out.println("Recebi essa porra de requisi��o GET no meu controller, do usuario");
	  }
}
