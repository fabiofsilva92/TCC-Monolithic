package fatec.br.tccmonolitico.services;


import fatec.br.tccmonolitico.entities.Transaction;
import fatec.br.tccmonolitico.respositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

}
