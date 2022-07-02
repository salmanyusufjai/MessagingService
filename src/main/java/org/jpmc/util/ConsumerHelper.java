package org.jpmc.util;

import org.jpmc.enums.OperationEnum;
import org.jpmc.enums.StatusEnum;
import org.jpmc.model.ProductInformation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsumerHelper {
    public static void printProductWiseTotalSale(Map<String, List<ProductInformation>> productWiseMap)
    {
        if(productWiseMap != null && !productWiseMap.isEmpty()) {
            printDetailReportHeader();

            productWiseMap.forEach((k, v) -> {

                double sum = v.stream().mapToDouble(ProductInformation::getPrice).sum();
                System.out.println(k + " ------- " + v.size() + " ---------- " + sum);
            });
            System.out.println("#==============================================================================#");
        }
    }

    public static Map<String,List<ProductInformation>> getProductWiseMessageList(List<ProductInformation> productInfoList, boolean includeAdjustment) {
        if(includeAdjustment) {
            return  productInfoList.stream()
                    .collect(Collectors.groupingBy(ProductInformation::getProductType));

        } else {
            return  productInfoList.stream().filter(c-> c.getAdjustmentOperation() == null)
                    .collect(Collectors.groupingBy(ProductInformation::getProductType));
        }

    }

    public static Map<String,List<ProductInformation>> getProductWisePendingMessageListAndUpdateStatus(List<ProductInformation> productInfoList, StatusEnum statusEnum,
                                                                                                 boolean includeAdjustment) {
        if(includeAdjustment) {
            return productInfoList.stream().filter(c -> c.getMessageStatus().equals(StatusEnum.PENDING)).map(b -> b.setMessageStatus(statusEnum))
                    .collect(Collectors.groupingBy(ProductInformation::getProductType));
        }
        else {
            return productInfoList.stream().filter(c ->c.getAdjustmentOperation() == null && c.getMessageStatus().equals(StatusEnum.PENDING)).map(b -> b.setMessageStatus(statusEnum))
                    .collect(Collectors.groupingBy(ProductInformation::getProductType));
        }
    }

    public static List<ProductInformation> getAdjustmentListOfEachProduct(List<ProductInformation> productInfoList) {
        return productInfoList.stream().filter(e ->
                e.getAdjustmentOperation() != null).collect(Collectors.toList());
    }

    public static void printAdjustmentReportHeader(){
        System.out.println("#=============================================================================#");
        System.out.println("#===========Messaging are paused for adjustment of existing records===========#");
        System.out.println("#=============================================================================#");
        System.out.println("#===========================Adjustment Report Started=========================#");
        System.out.println("#=============================================================================#");
    }
    public static void printDetailReportHeader(){
        System.out.println("#=============================================================================#");
        System.out.println("#============================Sales Detail Report==============================#");
        System.out.println("#=============================================================================#");
        System.out.println("#ProductType----TotalItemSale---TotalSaleValue================================#");
        System.out.println("#=============================================================================#");
    }

    public static double adjustPrice(double actualValue, double adjustment, OperationEnum operationEnum) {
        double newValue = 0L;
        switch (operationEnum){
            case ADD:
                newValue =  actualValue+adjustment;
                break;
            case SUBTRACT:
                newValue = actualValue-adjustment;
                break;
            case MULTIPLY:
                newValue = actualValue*adjustment;
                break;
        }
        return newValue;
    }
}
