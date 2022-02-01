package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        String result = ms.getMessage("hello", null, null);
        // hello 를 보내는데 args 는 없고 지역은 default
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(() -> ms.getMessage("no code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");

    }

    @Test
    void argumentMessage(){ // args 는 오브젝트 배열로 보내야 한다.
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang(){
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }


    @Test
    void enLang(){
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
