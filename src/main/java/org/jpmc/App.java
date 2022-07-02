package org.jpmc;

import org.jpmc.service.MessageManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class App 
{
    public static void main( String[] args )
    {
        if(args.length == 0)
        {
            System.out.println("Please provide message file path. Ex: java -jar jarname.jar E:\\MessagingService\\messageFile.txt");
            return;
        }
        MessageManager messageManager = MessageManager.getMessageManagerInstance();
        messageManager.startService();
        Callable  stopMessageService = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    Thread.sleep(50000);
                    return messageManager.stopService();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }
        } ;
        FutureTask f = new FutureTask(stopMessageService);
        Thread t = new Thread(f);
        t.start();


        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            while (!isServiceStopped(f)) {

                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    Thread.sleep(1000);
                } else {
                    System.out.println(line);
                    if(line.contains("PRODUCTTYPE"))
                        continue;
                    messageManager.addMessage(line);
                }


            }
            System.out.println("Application stop");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static Boolean isServiceStopped(FutureTask<Boolean> task) throws ExecutionException, InterruptedException {
        return task.isDone() && task.get();

    }
}
