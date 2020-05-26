package br.com.acaipaideguaweb.fcm;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAAIrWx1vQ:APA91bGguHjC_vqvEUBCPUjkk-RNU-AxPiynDPl94UDhnGplLwYJMtLwdxlnwoDDBHl9xwoyBWJc7-15I7gJ1Ol5tpEY7Bs6sHxgF0V8tO4e-2OBmd3_F3QQRbC3eOqsfYPYicyUXbLR";
   // private static final String FIREBASE_SERVER_KEY_CLIENTE = "AAAAc_r8pv0:APA91bENJme28ovKbAHCCKzWto26FN70wehtDN2XP9ztDBtLpH4sXZ9qUlfL32fTr5Jw7Ak0ECwsJ4flDM5PweNIc-LhxnnEExUuQxiapTDb2jVCh7X-tHD9oa18BM9j5EITz9dO26oO";

    
    @Async
    public CompletableFuture<FirebaseResponse> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        FirebaseResponse firebaseResponse = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, FirebaseResponse.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

}
