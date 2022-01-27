package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내무에 private 인데 static 하게 가지고 있는다.
    // 자바가 뜨면서 아래를 초기화 해서 딱1개 가지고 있는다
    private static final SingletonService instance = new SingletonService();

    // 인스턴스의 참조를 꺼내기 위해서는 아래 밖에 없다. 생성할 수 있는곳은 없다.
    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱클통 객체 로직 호출");
    }
}
