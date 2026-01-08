package dev.felipeazsantos.hexagonal.adapters.in.consumer;

import dev.felipeazsantos.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import dev.felipeazsantos.hexagonal.adapters.in.consumer.message.CustomerMessage;
import dev.felipeazsantos.hexagonal.application.ports.in.UpdateCustomerInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveValidatedCpfConsumer {

    @Autowired
    private UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @KafkaListener(topics = "tp-cpf-validated", groupId = "felipe")
    public void receive(CustomerMessage customerMessage) {
        var customer = customerMessageMapper.toCustomer(customerMessage);
        updateCustomerInputPort.update(customer, customerMessage.getZipCode());
    }
}
