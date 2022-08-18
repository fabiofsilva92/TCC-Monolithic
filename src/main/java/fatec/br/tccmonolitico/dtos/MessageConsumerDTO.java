package fatec.br.tccmonolitico.dtos;

import fatec.br.tccmonolitico.dtos.enums.MethodRequested;
import fatec.br.tccmonolitico.dtos.enums.ServiceRequested;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MessageConsumerDTO {

    private String id;
    private ServiceRequested service;
    private MethodRequested method;
    private List<Object> params;
    private Integer repetitions;

}

