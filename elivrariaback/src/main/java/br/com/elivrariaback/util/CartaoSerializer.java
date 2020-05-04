package br.com.elivrariaback.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.elivrariaback.dto.Cartao;

public class CartaoSerializer extends JsonSerializer <Cartao>{
	 @Override
	    public void serialize(Cartao cartao, JsonGenerator jsonGen,
	            SerializerProvider serProv) throws IOException,
	            JsonProcessingException {
	        jsonGen.writeStartObject();
	        jsonGen.writeNumberField("id", cartao.getId());
	        jsonGen.writeStringField("bandeiraDescricao", cartao.getBandeira().getDescricao());	   
	        jsonGen.writeNumberField("numeroCartao", cartao.getNumeroCartao());
	        jsonGen.writeStringField("nomeCartao", cartao.getNomeCartao());
	        jsonGen.writeNumberField("mesVencimento", cartao.getMesVencimento());
	        jsonGen.writeNumberField("anoVencimento", cartao.getAnoVencimento());
	        jsonGen.writeEndObject();

	    }

}
