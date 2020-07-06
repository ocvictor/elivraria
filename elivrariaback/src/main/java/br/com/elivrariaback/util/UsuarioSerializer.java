package br.com.elivrariaback.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.elivrariaback.dto.Usuario;

public class UsuarioSerializer extends JsonSerializer <Usuario>{
	 @Override
	    public void serialize(Usuario usuario, JsonGenerator jsonGen,
	            SerializerProvider serProv) throws IOException,
	            JsonProcessingException {
	        jsonGen.writeStartObject();
	        jsonGen.writeNumberField("id", usuario.getId());
	        jsonGen.writeStringField("nome", usuario.getNome());
	        jsonGen.writeStringField("sobrenome", usuario.getSobrenome());
	        jsonGen.writeStringField("genero", usuario.getGenero());
	        jsonGen.writeStringField("cpf", usuario.getCpf());
	        jsonGen.writeStringField("dtNascimento", usuario.getDtNascimento());
	        jsonGen.writeStringField("email", usuario.getEmail());
	        jsonGen.writeStringField("dddTelefone", usuario.getDddTelefone());
	        jsonGen.writeStringField("telefone", usuario.getTelefone());
	        jsonGen.writeBooleanField("ativo", usuario.isAtivo());
	        
	        jsonGen.writeEndObject();

	    }

}
