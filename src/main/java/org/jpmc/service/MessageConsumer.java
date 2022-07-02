package org.jpmc.service;

import org.jpmc.dao.ProcessedMessageDao;
import org.jpmc.enums.StatusEnum;
import org.jpmc.model.ProductInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.jpmc.util.ConsumerHelper.*;

/**
 * This is message consumer class.
 * This class will consumer message and process them
 * Print detail message and Adjust records
 */
public class MessageConsumer extends  Thread{
    private final List<ProductInformation> currentMessages = new ArrayList<>();
    private ProcessedMessageDao processedMessageDao = new ProcessedMessageDao();
    private final BlockingQueue<ProductInformation> blockingQueue;
    public  boolean isInterpreted = false;

    public MessageConsumer(BlockingQueue<ProductInformation> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public boolean isInterpreted() {
        return isInterpreted;
    }

    public void setInterpreted(boolean interpreted) {
        isInterpreted = interpreted;
    }


    @Override
    public void run() {
        System.out.println("Consumer thread started");
        try {
            int logMessageCounter = 10;
            int adjustmentCounter = 50;
            int messageCounter = 0;

            while (!isInterpreted()) {
                if(messageCounter != 0 &&   messageCounter % logMessageCounter == 0 ) {
                    processDetailsOn10thMessageReceived();
                }

                if(messageCounter != 0 &&  messageCounter % adjustmentCounter == 0 ) {
                    processAdjustment();
                    processedMessageDao.addAll(currentMessages);
                    currentMessages.clear();
                    messageCounter = 0;

                }

                ProductInformation productInformation = blockingQueue.poll(10, TimeUnit.SECONDS);
                if(null == productInformation)
                    continue;;
                addMessageToList(productInformation,currentMessages);
                messageCounter++;
            }
            System.out.println("Consumer thread End");

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void processDetailsOn10thMessageReceived()
    {
        Map<String, List<ProductInformation>> productWiseMap = getProductWisePendingMessageListAndUpdateStatus(currentMessages,
                StatusEnum.PROCESSED,false);
        printProductWiseTotalSale(productWiseMap);
    }

    private void processAdjustment() {
        printAdjustmentReportHeader();
        List<ProductInformation> adjustmentProduct = getAdjustmentListOfEachProduct(currentMessages);
        if(adjustmentProduct != null && !adjustmentProduct.isEmpty()) {
            Map<String, List<ProductInformation>> productWiseMap = getProductWiseMessageList(currentMessages, false);

            for (ProductInformation adjustInfo : adjustmentProduct) {
                List<ProductInformation> productInformationList = productWiseMap.get(adjustInfo.getProductType());

                productInformationList.forEach(e->e.setPrice(adjustPrice(e.getPrice(),
                        adjustInfo.getPrice(), adjustInfo.getAdjustmentOperation())));

                productInformationList.forEach(e -> System.out.println("Adjustment of " + adjustInfo.getPrice() + " on " +
                        e.getProductType() + " Completed. New price is : " + e.getPrice()));
            }
            System.out.println("#===========================After Adjustment Report===========================#");
            printProductWiseTotalSale(productWiseMap);
            System.out.println("#===========================Adjustment Report End=========================#");

        } else {
            System.out.println("#===========================No data to adjustment=========================#");
        }
    }

    private void addMessageToList(ProductInformation productInformation, List<ProductInformation> productInformationList){
        if(productInformation.getAdjustmentOperation() == null) {
            for (int i = 0; i < productInformation.getQuantity(); i++) {
                ProductInformation pInfo = new ProductInformation(productInformation.getProductType(),
                        productInformation.getPrice(),productInformation.getPriceUnitEnum(), 1,null );
                productInformationList.add(pInfo);
            }
        } else {
            productInformationList.add(productInformation);
        }
    }


}
