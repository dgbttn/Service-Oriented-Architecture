package MessageOrientedMiddleware;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RPCServer {

    private static final String PRC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(PRC_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(PRC_QUEUE_NAME);
            channel.basicQos(1);

            System.out.println("Awaiting RPC requests ...");

            Object monitor = new Object();
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                String response = "";
                try {
                    String message = new String(delivery.getBody(), "UTF-8");
                    response = SpellChecker.correct(message);
                } catch (UnirestException e) {
//                    e.printStackTrace();
                    System.out.println(" [.] " + e.toString());
                    response = "[ERROR] <Internal Server Error>";
                } finally {
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    // RabbitMq consumer worker thread notifies the RPC server owner thread
                    synchronized (monitor) {
                        monitor.notify();
                    }
                }
            };

            channel.basicConsume(PRC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {}));
            // Wait and be prepared to consume the message from RPC client
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}




