package fatec.br.tccmonolitico.dtos;

import fatec.br.tccmonolitico.dtos.enums.ServiceRequested;
import fatec.br.tccmonolitico.dtos.enums.TypeOfRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageConsumerDTO {

    private String id;
    private ServiceRequested service;
    private TypeOfRequest type;

}

