package com.picpay.api.services;

import com.picpay.api.domains.user.User;
import com.picpay.api.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO NotificationRequest = new NotificationDTO(email, message);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", NotificationRequest, String.class);

        if(!responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Error sending notification to " + email);
            throw new Exception("Error sending notification, maybe service is down");
        }
    }

}
