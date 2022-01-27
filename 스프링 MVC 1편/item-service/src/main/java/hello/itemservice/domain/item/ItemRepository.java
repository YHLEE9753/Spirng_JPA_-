package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //component scan 의 대상이 된다.
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static
    // static 싱글통의 경우 동시 접근 가능 이런경우 HashMap 쓰면 안되고 ConcurrentHashMap 을 사용해야 한다.!!
    private static long sequence = 0L; //static - 어차피 전부 싱글톤이긴하다.
    // long 또한 동시접근이 가능하기 때문에 위험하다. 이런경우 AtomicLong 같은 것을 써야한다.

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values()); // 한번 감싸서 반환하면 ArrayList 에 값을 담아도 실제 store 에 변화가 없어서 좋다.
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        // 이 경우 id 는 쓰이지 않는다. 코드의 중복이 되겠지만 명확성을 위해 Item class 이외에도
        // ItemDetailDTO 이런 클래스를 만들어서 name, price, qunatity 만 있는 클래스를 사용하는 것이 낫다.
        // Item 사용시 setId() 를 사용할수도 있고 다른 개발자들은 id 가 쓰이는것인가? 하는 의문을 가질수 있다.
        // 다시 말해 명확성이 코드 중복보다는 우선시되는 가치이다.
    }

    public void clearStore(){
        store.clear();
    }
}
