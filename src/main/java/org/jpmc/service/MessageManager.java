package org.jpmc.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This is Manager class to start and stop message service.
 * This will also help to send message producer
 */
public class MessageManager {
    private static MessageManager INSTANCE = null;
    private MessageProducer messageProducer;
    private MessageConsumer messageConsumer;

    private  MessageManager() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(50);
        messageProducer = new MessageProducer(blockingQueue);
        messageConsumer = new MessageConsumer(blockingQueue);
    }

    public static MessageManager getMessageManagerInstance(){
        if(INSTANCE == null) {
            synchronized (MessageManager.class){
                INSTANCE = new MessageManager();
            }
        }
        return INSTANCE;
    }

    public synchronized boolean startService()
    {
        if(isServiceStarted())
        {
            System.out.println("Message service is already started...");
            throw new IllegalStateException("Message service is already started");
        }
        messageProducer.start();
        messageConsumer.start();
        return true;
    }

    public synchronized boolean stopService()
    {
        if(!isServiceSopped())
        {
            messageProducer.setInterpreted(true);
            messageConsumer.setInterpreted(true);
            return true;
        }
        System.out.println("Message service is already stop...");
        throw new IllegalStateException("Message service is already stopped");

    }

    public boolean addMessage(String message){
        if(isServiceStarted() && !isServiceSopped())
            return messageProducer.addMessage(message);
        else {
            System.out.println("Message service is not running. Please start service ...");
            throw new IllegalStateException("Please start Message service before add message");
        }
    }

    private  boolean isServiceStarted() {
        return  messageProducer.isAlive() && messageConsumer.isAlive();
    }
    private  boolean isServiceSopped() {
        return  messageProducer.isInterpreted() && messageConsumer.isInterpreted();
    }

}
