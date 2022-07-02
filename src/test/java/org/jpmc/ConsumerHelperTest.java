package org.jpmc;

import org.jpmc.enums.OperationEnum;
import org.jpmc.enums.PriceUnitEnum;
import org.jpmc.enums.StatusEnum;
import org.jpmc.model.ProductInformation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.jpmc.util.ConsumerHelper.*;

public class ConsumerHelperTest {
    static List<ProductInformation> productInformationList;

    @Before
    public void before(){
        productInformationList = new ArrayList<>();

        productInformationList.add(new ProductInformation("Apple",20.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",5.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",5.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",5.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",5.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",5.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",5.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",5.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",5.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Apple",10.00, PriceUnitEnum.P,null, OperationEnum.ADD));
        productInformationList.add(new ProductInformation("Banana",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Banana",10.00, PriceUnitEnum.P,null, OperationEnum.MULTIPLY));
        productInformationList.add(new ProductInformation("Mango",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Mango",15.00, PriceUnitEnum.P,1,null));

        productInformationList.add(new ProductInformation("Orange",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Orange",15.00, PriceUnitEnum.P,1,null));

        productInformationList.add(new ProductInformation("Grapes",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",15.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",10.00, PriceUnitEnum.P,1,null));
        productInformationList.add(new ProductInformation("Grapes",30.00, PriceUnitEnum.P,null,OperationEnum.ADD.SUBTRACT));

    }

    @Test
    public void getProductWiseMessageListTest()
    {
        Map<String, List<ProductInformation>> productWiseMessageList = getProductWiseMessageList(productInformationList, false);
        Assert.assertTrue(productWiseMessageList.get("Apple").size() == 9);
    }

    @Test
    public void getProductWiseMessageListIncludeAdjustmentTest()
    {
        Map<String, List<ProductInformation>> productWiseMessageList = getProductWiseMessageList(productInformationList, true);
        Assert.assertTrue(productWiseMessageList.get("Apple").size() == 10);
    }

    @Test
    public void getProductWisePendingMessageListAndUpdateStatusTest(){
        Map<String, List<ProductInformation>> productWiseStatusUpdateMap = getProductWisePendingMessageListAndUpdateStatus(productInformationList, StatusEnum.PROCESSED, false);
        productWiseStatusUpdateMap.entrySet().forEach(k -> k.getValue().stream().forEach(
                v -> Assert.assertTrue(v.getMessageStatus().equals(StatusEnum.PROCESSED))));
    }

    @Test
    public void getProductWisePendingMessageListAndUpdateStatusForAdjustmentTest(){
        Map<String, List<ProductInformation>> productWiseStatusUpdateMap = getProductWisePendingMessageListAndUpdateStatus(productInformationList, StatusEnum.PROCESSED, true);
        productWiseStatusUpdateMap.entrySet().forEach(k -> k.getValue().stream().filter
                (f -> f.getAdjustmentOperation() != null).forEach(
                v -> Assert.assertTrue(v.getMessageStatus().equals(StatusEnum.PROCESSED))));
    }

    @Test
    public void  getAdjustmentListOfEachProductTest() {
        List<ProductInformation> adjustmentListOfEachProduct = getAdjustmentListOfEachProduct(productInformationList);
        adjustmentListOfEachProduct.stream().forEach(a -> Assert.assertTrue(a.getAdjustmentOperation() != null));
    }

    @Test
    public void  adjustPriceTest() {
        double add = adjustPrice(5,10,OperationEnum.ADD);
        double subtract = adjustPrice(5,10,OperationEnum.SUBTRACT);
        double multiply = adjustPrice(5,10,OperationEnum.MULTIPLY);
        Assert.assertTrue(add == 15);
        Assert.assertTrue(subtract == -5);
        Assert.assertTrue(multiply == 50);

    }
}
