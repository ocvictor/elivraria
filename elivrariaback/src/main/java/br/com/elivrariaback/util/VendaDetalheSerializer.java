package br.com.elivrariaback.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.elivrariaback.dto.VendaDetalhe;

public class VendaDetalheSerializer extends JsonSerializer <VendaDetalhe>{
	 @Override
	    public void serialize(VendaDetalhe vendaDetalhe, JsonGenerator jsonGen,
	            SerializerProvider serProv) throws IOException,
	            JsonProcessingException {
	        jsonGen.writeStartObject();
	        jsonGen.writeNumberField("id", vendaDetalhe.getId());
	        jsonGen.writeStringField("dataVenda", vendaDetalhe.getDataVenda());
	        jsonGen.writeStringField("usuarioNome", vendaDetalhe.getUsuario().getNome() + " " + vendaDetalhe.getUsuario().getSobrenome());
	        jsonGen.writeStringField("statusDescricao", vendaDetalhe.getStatusVenda().getDescricao());
	        jsonGen.writeNumberField("qtdVenda", vendaDetalhe.getQtdVenda());
	        jsonGen.writeNumberField("totalVenda",vendaDetalhe.getTotalVenda());
	        jsonGen.writeEndObject();

	    }

}
