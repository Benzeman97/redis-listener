package com.benz.redis.api.listener;
import com.benz.redis.api.model.Product;
import org.springframework.stereotype.Component;

@Component
public class Consumer  {


    /*private ObjectMapper objectMapper;*/

   /* @Override
    public void onMessage(Message message, byte[] bytes) {

        try {
            objectMapper = new ObjectMapper();

           Product product= objectMapper.readValue(message.toString(), Product.class);

           logger.info(" productId  : "+product.getProductId());
           logger.info(" productName  : "+product.getName());
           logger.info(" quantity  : "+product.getQty());
           logger.info(" price "+product.getPrice());


        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }*/

    public void handleMessage(Product product)
    {
        System.out.println(product);
    }

}
