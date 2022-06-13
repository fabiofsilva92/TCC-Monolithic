package fatec.br.tccmonolitico.dtos;

import fatec.br.tccmonolitico.entities.Cambio;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CambioDTO {

    private String from;

    private String to;

    private Double conversionFactor;

    private Double convertedValue;

    public CambioDTO(Cambio cambio){
        this.from = cambio.getFrom();
        this.to = cambio.getTo();
        this.conversionFactor = cambio.getConversionFactor();
        this. convertedValue = cambio.getConvertedValue();
    }
}
