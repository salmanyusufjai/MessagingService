package org.jpmc.service;

import org.jpmc.enums.OperationEnum;
import org.jpmc.enums.PriceUnitEnum;
import org.jpmc.model.ProductInformation;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * This is message producer class responsible to produce message
 * and send to the consumer
 */
public class MessageProducer extends Thread {

    private BlockingQueue<ProductInformation> messageBlockingQueue;
    private BlockingQueue<ProductInformation> producerQueue = new ArrayBlockingQueue<>(50);
    private  boolean isInterpreted  = false;

    public boolean isInterpreted() {
        return isInterpreted;
    }

    public void setInterpreted(boolean interpreted) {
        isInterpreted = interpreted;
    }
    public MessageProducer(BlockingQueue<ProductInformation> messageBlockingQueue) {
        this.messageBlockingQueue = messageBlockingQueue;
    }

    /**
     * This method use to add message to the producer queue that will consume
     * by consumer
     * @param msgString
     * @return true if message added successfully
     */
    public boolean addMessage(String msgString) {
        boolean isAdded = false;

        if(null != msgString && !msgString.isEmpty()) {
            try {
                List<String> messageString = Arrays.stream(msgString.split(",")).collect(Collectors.toList());
                ProductInformation p = new ProductInformation();
                p.setProductType(messageString.get(0));
                p.setPrice(Double.parseDouble(messageString.get(1)));
                p.setPriceUnitEnum(PriceUnitEnum.valueOf(messageString.get(2)));
                p.setQuantity(Integer.parseInt(messageString.get(3)));
                if (messageString.size() > 4)
                    p.setAdjustmentOperation(OperationEnum.valueOf(messageString.get(4)));

                producerQueue.put(p);
                isAdded = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return  isAdded;
    }

    @Override
    public void run() {
        System.out.println("Producer thread started");
        while (!isInterpreted()) {
            try {
                ProductInformation productInformation = producerQueue.poll(10, TimeUnit.SECONDS);
                if(null == productInformation)
                    continue;
                messageBlockingQueue.put(productInformation);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Producer thread End");

    }

}
