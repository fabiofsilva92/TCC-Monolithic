package fatec.br.tccmonolitico.consumer;

import fatec.br.tccmonolitico.dtos.BookDTO;
import fatec.br.tccmonolitico.dtos.MessageConsumerDTO;
import fatec.br.tccmonolitico.dtos.enums.ServiceRequested;
import fatec.br.tccmonolitico.entities.Cambio;
import fatec.br.tccmonolitico.proxy.BookProxy;
import fatec.br.tccmonolitico.proxy.CambioProxy;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class KafkaMessageConsumer {

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupID;

    @Value(value = "${topic.name}")
    private String topic;

    @Autowired
    private BookProxy bookProxy;

    @Autowired
    private CambioProxy cambioProxy;

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenTopicLogs(ConsumerRecord<String, MessageConsumerDTO> record){
//        log.info("Received message partition: "+record.partition());
        log.info("Received message: "+record.value());
        processMessage(record.value());
//        Object valor = record.value();
//        MessageConsumerDTO mensagem = (MessageConsumerDTO) valor;
//        log.info("Received message: "+((MessageConsumerDTO) record.value()).getType());
//        log.info("SEGUNDA MENSAGEM NESSE CARALHO: "+mensagem.getSecondMessage());
    }

    private void processMessage(MessageConsumerDTO message){

        if(message.getService() == ServiceRequested.BOOK) processBookRequest(message);
        else if(message.getService() == ServiceRequested.CAMBIO) processCambioRequest(message);
        else throw new RuntimeException("Message with problems, re-send the message");



//        Long start = System.nanoTime();
//        log.info("Initializing ProcessMessage");
//        System.out.println(message.toString());
//        Long end = System.nanoTime();
//        System.out.println("Tempo passado no metodo :" + (end-start) + "nanossegundos");
//        System.out.println("Tempo passado no metodo segundos :" + (end-start)*(Math.pow(10, -9)) + "s");

    }

    private void processBookRequest(MessageConsumerDTO message){
        switch (message.getMethod()){
            case GET_BOOK:
                //todo something

                break;
            case FIND_BOOK_BY_ID:
                break;
            case FIND_ALL_BOOK:
                Long start = System.nanoTime();
                ResponseEntity<List<BookDTO>> all = bookProxy.findAll();
                Long end = System.nanoTime();
                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} nanossegundos", message.getMethod(), (end-start));
//                log.info("MICROSSERVICE - Tempo passado no metodo :" + (end-start) + " nanossegundos");
                break;
            case CREATE_BOOK:
                break;
            case UPDATE_BOOK:
                break;
            case DELETE_BOOK:
                break;

            default:
                System.out.println("Erro nao encontrado");
        }
    }

    private void processCambioRequest(MessageConsumerDTO message){
        switch (message.getMethod()){
            case GET_CAMBIO:
                break;
            case FIND_CAMBIO_BY_ID:
                break;
            case FIND_ALL_CAMBIO:
                //todo something
                Long start = System.nanoTime();
                ResponseEntity<List<Cambio>> all = cambioProxy.findAll();
                Long end = System.nanoTime();
                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} nanossegundos", message.getMethod(), (end-start));
//                log.info("Tempo passado no metodo :" + (end-start) + " nanossegundos");
                break;
            case CREATE_CAMBIO:
                break;
            case UPDATE_CAMBIO:
                break;
            case DELETE_CAMBIO:
                break;

            default:
                System.out.println("Erro nao encontrado");
        }
    }

}
