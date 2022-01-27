package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data // 되게 위험하다. Getter Setter 을 쓰자!!
//@Getter @Setter
public class Item {

    private long id;
    private String itemName;
    private Integer price; // Integer 을 쓰는 이유는 price 나 quantity 가 null 로 들어갈 수 있다는 의미
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
