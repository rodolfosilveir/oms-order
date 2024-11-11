package br.com.ambev.oms_order_manager.domain.model;

import java.math.BigDecimal;

import lombok.*;

@Builder
@Getter
@Generated
@Setter(value = AccessLevel.PRIVATE)
public class Item {

    private Integer id;

    private String name;

    private String description;

    private String category;

    private Integer volumeMililiters;

    private BigDecimal price;

    private Integer quantity;

    public BigDecimal getConvertedVolume() {
        if(volumeMililiters > 1000) {
            return BigDecimal.valueOf(volumeMililiters).divide(BigDecimal.valueOf(1000));

        }
        return BigDecimal.valueOf(volumeMililiters);
    }
    
    public String getLiterage() {
        if(volumeMililiters > 1000) {
            return "L";
        }
        return "ml";
    }
    
}
