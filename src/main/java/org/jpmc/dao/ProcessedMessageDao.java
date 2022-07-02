package org.jpmc.dao;

import org.jpmc.model.ProductInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessedMessageDao {
    List<ProductInformation> productInformationList = new ArrayList<>();

    public boolean add(ProductInformation productInformation)
    {
        return productInformationList.add(productInformation);
    }

    public boolean addAll(List<ProductInformation> productInformationList)
    {
        return productInformationList.addAll(productInformationList);
    }

    public List<ProductInformation> getProductInformationByType(String productType)
    {
        return productInformationList.stream().filter(p -> p.getProductType().equals(productType)).collect(Collectors.toList());
    }
}
