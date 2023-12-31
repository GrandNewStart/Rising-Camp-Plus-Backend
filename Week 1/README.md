# 1주차 개발일지

## 과제 계획서

### 1주차 과제

    1. 자바 강의 수강
        - 필수 강의 약 10시간
        - 선택 강의 약 4시간
        - 토/일요일 활용하여 모두 수강
    2. 백준 문제 풀이
    3. 요구사항 개발 및 Git 활용


## 핵심 개념 정리

### 객체 지향 프로그래밍

    절차적/구조적 프로그래밍이 '절차'와 '구조'로 구성된다면,
    객체 지향 프로그래밍은 data와 action으로 구성된 object로 구성된다.
    모든 객체는 특정한 상태(state)와 동작(action)을 가진다는 뜻.
    Class는 템플릿, Object는 템플릿으로 생성한 인스턴스.
    객체 지향 프로그래밍의 핵심 개념으로 캡슐화와 추상화가 있다.
    Class를 정의할 때, getter/setter처럼 클래스 데이터가 직접 외부로 노출되지 않게 구현하는 것을 캡슐화라고 한다.
    캡슐화의 이유: 클레스의 데이터에 대한 '추적 가능한 접근'을 구현할 수 있다. 디버깅, 동작 추가/수정의 편의.
    클래스의 객체를 사용하는 사람이 그 클래스의 동작 구조를 몰라도 사용할 수 있게 구현하는 것을 추상화라고 한다.
    MotorBike 클래스의 accelerate, decelerate 등은 어떻게 구현되어 있는지 몰라도 직관적으로 사용 가능하다. 
    결국 객체 지향 프로그램은 추상화와 캡슐화 개념을 잘 활용해 클래스를 잘 구현하는 것이 핵심.
    클래스를 만들 때, 이 클래스의 인스턴스가 어떤 속성을 가지고 어떤 동작을 가지는지에 대한 고민을 해야한다.
    
### 참조 자료형

    클래스 인스턴스는 참조 자료형이다.
    이는 기본 자료형(int, boolean, string 등)과 대비되는 성질을 가지고 있다.
    기본 자료형의 변수는 생성될 때 Stack에 저장된다.
    클래스 객체의 경우, 실제 데이터는 Heap에 저장되고, Stack에는 해당 데이터의 Heap 주소만 저장된다.
    따라서 한 객체에 다른 객체를 대입하면 그 둘은 동일한 데이터에 대한 참조를 가지게 된다. Call by reference.
    값이 복사되는 것이 아니기 때문에 한쪽을 변경하면 다른 한쪽도 같이 변경된다.
    Wrapper class란, 특정 자료형을 감싸서 마치 클래스 객체처럼 보이도록 한 것이다. (boolean -> Boolean, int -> Integer 등)
    기본 자료형이 제공하지 못하는 기능을 추가로 구현할 수 있는 장점이 있다.
    
### 객체 지향 프로그래밍으로 돌아가기

    클래스는 데이터(상태)와 동작(행동)으로 구성된 '템플릿'이다.
    해당 클래스의 객체가 어떤 특성을 가지고 어떤 동작을 하는지 고민하고 설계해야 한다.
    사용자의 관점에서 생각해야한다.
    한 클래스의 객체를 다른 클래스의 데이터로 사용하는 것을 Object Composition이라고 한다.
    상속을 사용하는 이유: 비슷한 기능을 중복 구현하는 일을 줄여준다.
    단 중복되는 기능이 많더라도, 개념적인 종속관계가 없다면 상속을 사용하지 않는 편이 좋다.
    모든 Java 클래스는 기본적으로 Object 클래스를 상속하고 있다. 따라서 아무것도 상속하지 않아도 기본적으로 가지는 기능들이 있음.
    오버라이드: 부모 클래스의 메서드를 자식 클래스에서 새로 구현하는 것.
    상속 클래스의 생성자를 호출할 때, super()를 명시적으로 호출하지 않아도 자동으로 호출된다.
    Java는 다중 상속을 지원하지 않지만, 상속 계층을 지원한다.
    한 클래스가 한번에 여러 클래스를 상속할 순 없지만 상속 클래스를 상속하는 클래스는 만들 수 있다는 소리.
    object instanceof Object -> true/false
    추상 클래스: 클래스를 정의하되, 그 구체적인 적용법을 정할 필요가 없을 때 사용한다.
    추상 클래스는 단독으로 사용될 수 없으며 반드시 상속을 통해 구현해야한다.
    추상 클래스를 상속할 때는 반드시 부모가 되는 추상 클래스의 메서드를 구현해야한다.
    추상 클래스는 일반 메서드를 가질 수도 있고, 추상 메서드를 가질 수도 있다.
    추상 클래스는 다른 추상 클래스를 상속할 수 있고, 이 경우, 부모 클래스의 메서드를 구현할 필요는 없다.
    인터페이스: 정의만 있고 구현은 없는 클래스의 설계도. 추상 메서드만 가지는 추상 클래스라고 볼 수 있다.
    인터페이스는 동일한 메서드를 지니는 전혀 다른 클래스들을 구현할 때 유용하다.
    인터페이스는 다른 인터페이스를 상속할 수 있다.
    인터페이스도 상수는 가질 수 있다.
    인터페이스도 구현된 함수를 가질 수는 있다 (default 메서드).
    인터페이스는 다중 상속(인터페이스는 클래스가 아니기 때문에 상속은 아니지만)이 가능하다.
    인터페이스는 '계약'이다.
    다형성: 인터페이스와 추상 클래스를 통해 같은 메서드에 다른 기능을 구현하는 것.
    
### 컬렉션

    Java는 List, Set, Queue, Map의 기본 컬렉션 자료형을 제공한다.
    
    List
        ㄴArrayList
        ㄴVector
        ㄴLinkedList
    List는 각 요소의 위치를 정확히 알아야 할 때 유용하다.
    List는 불변 리스트, ArrayList, Vector, LinkedList는 가변 리스트로, 서로 전환이 가능하다.
    ArrayList에 비해 LinkedList는 접근은 느리지만 요소 추가/삭제가 빠르다.
    ArrayList가 제공하지 않는 동기화 기능을 Vector는 제공한다.
    동기화라는 것은 Thread Safe 하다는 의미로, 한번에 하나의 스레드만이 접근할 수 있다는 뜻이다.
    루프 도중에 리스트 아이템에 변화를 주려면 Iterator를 쓰는 것이 좋다.
    커스텀 정렬을 구현할 땐, Comparator 구현체를 만들어서 사용한다.
    
    Set
        ㄴHashSet
        ㄴLinkedHashSet
        ㄴTreeSet
    Set은 중복을 허용하지 않는 컬렉션이다.
    Set은 불변 리스트, HashSet, LinkedHashSet, TreeSet은 가변 리스트로, 서로 전환이 가능하다.
    HashSet, LinkedHashSet은 HashTable에 기반한다.
    HashTable은 빠른 검색/추가/삭제 속도를 제공한다.
    Java는 hashcode()를 통해 해싱 함수를 제공한다.
    HashSet은 순서를 보장하지 않지만 LinkedHashSet은 순서를 보장한다.
    TreeSet은 Tree 자료형에 기반한다.
    Tree는 일정한 규칙(오름차순)에 따라 그래프로 정렬한 자료형이다.
    Tree는 요소간 비교가 빠르며 검색/삭제/추가도 꽤 빠른 편이다.
    TreeSet은 floor, lower, upper, ceiling, higher subSet, headSet, tailSet 등 다양한 연산을 제공한다.
    
    Queue
        ㄴPriorityQueue
    Queue는 원하는 순서대로 요소를 자동으로 정렬하고 싶을 때 유용하다.
    Comparator 객체를 생성자에 인자로 넣어 커스텀 정렬을 구현할 수 있다.
    
    Map
        ㄴHashMap
        ㄴHashTable
        ㄴLinkedHashMap
        ㄴTreeMap
    Map은 key-value 구조로 구성된 컬렉션이다.
    HashMap은 삽입 순서와 정렬을 보장하지 않는다.
    HashTable은 Thread Safe하다. 마찬가지로 삽입 순서와 정렬을 보장하지 않는다.
    LinkedHashMap은 삽입 순서를 보장하지만 정렬은 보장하지 않는다.
    TreeMap은 정렬을 보장한다.
    TreeMap은 higherKey, ceilingKey, lowerKey, flooerKey, firstEntry, lastEntry, subMap 등 다양한 연산을 제공한다.
    
    이름에 Hash가 들어가면 순서가 보장되지 않고
    Linked가 들어가면 순서가 보장되고
    Tree가 들어가면 다양한 비교 관련 연산자가 있다는 것을 알 수 있다.
    
### 제네릭
    
    다른 자료형에 대한 동일한 기능을 구현하고자 할 때 제네릭을 사용한다.
    제네릭은 정해지지 않은 자료형을 보통 T로 표현한다. 꼭 T가 아니어도 상관은 없다.
    T를 특정 클래스의 자식 클래스로 한정하고 싶을 때 T extends {Class}로 표현하면 된다.
    제네릭은 클래스 뿐만 아니라 메서드를 정의할 때도 사용할 수 있다.
    와일드카드는 '어떤 타입이든 될 수 있다'는 뜻이다.
    와일드카드는 extends/super의 한정자와 함께 사용하여 타입의 범위를 제한한다.
    Upper bound 와일드카드는 타입의 상한 경계를 정하며, exends를 붙인다. ? extends {Class}
    ? extends Number 이렇게 쓰면 Number 타입 이하의 클래스만 사용 가능. 부모 클래스는 사용할 수 없다.
    Lower bound 와일드카드는 타입의 하한 경계를 정하며, super를 붙인다. ? super {Class}
    ? super Number 이렇게 쓰면 Number 타입 이상의 클래스만 사용 가능. 자식 클래스는 사용할 수 없다.
    
### 예외 처리
    
    예외가 발생하면 그 이후의 코드는 실행되지 않는다.
    StackTrace는 예외 발생 지점을 역추적 가능하도록 발생 지점을 표시해준다.
    NullPointerException: 값이 null인 변수에 접근할 때 발생하는 가장 흔한 에러
    ArrayIndexOutOfBoundsException: 배열의 최대 인덱스를 벗어난 값을 호출할 때 발생하는 흔한 에러
    try-catch 문을 쓰면 StackTrace를 보기 위해 Exception.printStackTrace()를 호출해줘야한다.
    catch문을 여러번 호출하여 서로 다른 종류의 예외를 처리할 수 있다.
    catch (Exception ex) 이렇게 호출하면 모든 예외를 다 잡을 수 있다.
    finally { ... } - 예외가 발생하더라도 반드시 실행이 필요한 코드가 있는 경우 유용하다. 예: scanner.close()
    finally 구문 안에서 예외가 발생할 경우, 나머지 코드는 실행되지 않는다. 기본적으로 finally 구문은 거의 항상 실행된다.
    RuntimeException과 InterruptedException은 다르게 처리된다.
    예외에도 서열이 있다.
    Throwable > Error > Exception/Error > InterruptedException/RuntimeException > NullPointerException
    참고로 Error는 Exception과 다르게 handle 할 수 없다.
    Checked Exception: 발생 시, handle 하거나 throw 해야한다. Interrupted Exception. 
    Unchecked Exception: 발생해도 그냥 넘어갈 수 있다. RuntimeException.
    다수의 catch 문을 작성할 땐, Exception 사이의 계층을 고려하여 범위가 작은 것부터 차례로 써야 한다.
    
## 진행상황

    토~일요일을 타 다 들으려고 했던 강의는 생각보다 분량이 훨씬 많았다.
    주중에 퇴근 후, 퇴근 전으로 계속 조금씩 듣고 있으며 목요일부로 필수 강의는 시청 완료.
    화요일부터 백준 문제 풀이 시작. 목요일부로 백준 문제 완료.
    금요일 부로 요구사항 개발 시작.
    토요일 1시 경 모든 과제 완료.
    나머지 시간동안 선택강의 수강.


## 피드백



## 참고자료
