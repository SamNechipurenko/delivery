package com.delivery;

import com.delivery.model.Client;
import com.delivery.model.Employee;
import com.delivery.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClientServiceTest {

    @MockBean
    ClientService clientService;

    @Test()
    void shouldCreateClient(){
        Client client1 = new Client();
        client1.setClientEmail("email");
        client1.setClientEmail("name");
        client1.setClientId(2);

        Client client2 = new Client();
        client2.setClientEmail("email");
        client2.setClientEmail("name");
        client2.setClientId(2);

        Mockito.when(clientService.create(client1)).thenReturn(client1);
        assertEquals(client1,clientService.create(client1));
    }

}
