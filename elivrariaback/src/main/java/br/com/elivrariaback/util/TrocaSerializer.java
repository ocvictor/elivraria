package br.com.elivrariaback.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.elivrariaback.dto.Troca;
import br.com.elivrariaback.dto.VendaDetalhe;

public class TrocaSerializer extends JsonSerializer <Troca>{
	 @Override
	    public void serialize(Troca troca, JsonGenerator jsonGen,
	            SerializerProvider serProv) throws IOException,
	            JsonProcessingException {
	        jsonGen.writeStartObject();
	        jsonGen.writeNumberField("id", troca.getId());
	        jsonGen.writeStringField("dataSolicitacao", troca.getDataSolicitacao());
	        jsonGen.writeStringField("usuarioNome", troca.getVendaDetalhe().getUsuario().getNome() + " " + troca.getVendaDetalhe().getUsuario().getSobrenome());
	        jsonGen.writeNumberField("vendaDetalheId", troca.getVendaDetalhe().getId());
	        jsonGen.writeStringField("titulo", troca.getItemVenda().getLivro().getTitulo());
	        jsonGen.writeNumberField("qtdTroca",troca.getQtdTroca());
	        jsonGen.writeEndObject();

	    }

}
