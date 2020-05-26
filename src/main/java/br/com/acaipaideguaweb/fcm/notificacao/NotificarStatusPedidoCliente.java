package br.com.acaipaideguaweb.fcm.notificacao;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.acaipaideguaweb.fcm.AndroidPushNotificationsService;
import br.com.acaipaideguaweb.fcm.FirebaseResponse;
import br.com.acaipaideguaweb.resources.dto.VendaDTO;
@Service
public class NotificarStatusPedidoCliente {
	
	private static final Logger log = LoggerFactory.getLogger(NotificarStatusPedidoCliente.class);
	
	@Autowired
    AndroidPushNotificationsService androidPushNotificationsService;
		

	
	public ResponseEntity<String> send(String idDispositivoCliente, VendaDTO vendaDTO) throws JSONException, JsonProcessingException {
		

        JSONObject body = new JSONObject();
        // JsonArray registration_ids = new JsonArray();
        // body.put("registration_ids", registration_ids);
        body.put("to", idDispositivoCliente);
        body.put("priority", "high");
        // body.put("dry_run", true);


        // notification.put("icon", "myicon");
        //data.put("key1", "value1");
        //data.put("key2", "value2");
        
    	//ObjectMapper mapper = new ObjectMapper();
       // String jsonInString = mapper.writeValueAsString(pedido);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(vendaDTO);
        
        
        JSONObject data = new JSONObject(jsonInString);
        //data.put("data", pedido.getId());
        //data.put("statusPedido", pedido.getStatus());
        System.out.println("json"+data);
        
        JSONObject notification = new JSONObject();
        notification.put("click_action", "activity_pedido");
        notification.put("title", "DELIVERY PAIDÉGUA");
        notification.put("body", "Status do pedido = "+vendaDTO.getStatus());

        System.out.println(notification.get("click_action"));
        body.put("notification", notification);
        body.put("data", data );

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            FirebaseResponse firebaseResponse = pushNotification.get();
            if (firebaseResponse.getSuccess() == 1) {
                log.info("push notification sent ok!");
            } else {
                log.error("error sending push notifications: " + firebaseResponse.toString());
            }
            return new ResponseEntity<>(firebaseResponse.toString(), HttpStatus.OK);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("the push notification cannot be send.", HttpStatus.BAD_REQUEST);
    }
	


}
