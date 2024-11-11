package br.com.ambev.oms_order_manager.adapter.in.rest.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ambev.oms_order_manager.domain.model.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(access = AccessLevel.PRIVATE)
public class ItemResponse {

    private Integer id;

    private String name;

    private String description;

    private String category;

    private BigDecimal volume;

    private String literage;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalValue;

    public static List<ItemResponse> fromDomain(List<Item> items) {
        return items.stream()
                .map(item -> ItemResponse.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .description(item.getDescription())
                        .category(item.getCategory())
                        .volume(item.getConvertedVolume())
                        .literage(item.getLiterage())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .totalValue(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .build())
                .toList();
    }
    
    
}
