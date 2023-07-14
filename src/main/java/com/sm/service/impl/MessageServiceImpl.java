package com.sm.service.impl;

import com.sm.model.Message;
import com.sm.repository.MessageRepository;
import com.sm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public String saveMessage(int i) {
        if (i == 1) {
            messageRepository.save(new Message("Custom Message With Existing User Transaction"));
            return "Message Saved Existing Transaction";
        }
        throw new RuntimeException("Message Exception occur");
    }


    // if exception occur , previous transactions will be processed.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String saveMessageWithNewTransaction(int i) {
        if (i == 3) {
            messageRepository.save(new Message("Custom Message With New Transaction"));
            return "Message Saved With New Transaction";
        }
        throw new RuntimeException("Message Exception occur");
    }

    @Override
    public String saveMessageWithoutTransaction(int i) {
        if (i == 1) {
            messageRepository.save(new Message("Custom Message Without Transaction Annotation"));
            return "Message Saved Without Transaction";
        }
        throw new RuntimeException("Message Exception occur");
    }


    // if it is directly called from controller then new transaction will be created
    // if it is called from previous service class with Required propagation then same transaction will be used
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String saveMessageWithSupportTransaction() {
        messageRepository.save(new Message("Custom Message With SUPPORTS Transaction"));
        return "Message Saved With SUPPORTS Transaction";
    }


    // if it is directly called from controller then exception occur cuz no transaction is found
    // if it is called from previous service class with Required propagation then same transaction will be used
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public String saveMessageWithMandatoryTransaction() {
        messageRepository.save(new Message("Custom Message With Mandatory Transaction"));
        return "Message Saved With Mandatory Transaction";
    }

}



