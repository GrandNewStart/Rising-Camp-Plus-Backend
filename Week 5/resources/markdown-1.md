# JPA의 N+1 문제

프록시 객체: 어떤 Entity 객체를 상속한 가짜(껍데기) 객체. 실제 데이터는 없지만 원본 객체에 대한 참조를 가지고 있다. 따라서 타입 비교를 할 때 instance of 연산자를 사용한다.

JPA에서 Entity를 조회할 때, 즉시 로딩을 하는 방법과 지연 로딩을 하는 방법이 있다.
- 즉시 로딩: 실제 데이터를 조회
- 지연 로딩: 프록시 데이터를 조회

```java
// 지연 로딩을 사용할 경우, 다음과 같은 상태에선 team은 아직 실체가 없는 프록시 객체라고 할 수 있다.
Team team = member.getTeam();
// 다음과 같이 member 객체의 컬럼 값에 직접 접근할 때가 되서야 실제 데이터가 불러와진다.
team.getName();
```

## N+1 문제

즉시로딩을 사용할 경우, 연결된 객체까지도 한꺼번에 쿼리가 진행된다. Team이라는 엔티티를 불러올 때, Team이 가지고 있는 n개의 Member 엔티티도 같이 쿼리가 되는 것이다. 즉, 하나의 쿼리에 대해 실제로 수행되는 쿼리는 1에다 n을 포함한, (n+1)개의 쿼리가 진행되는 것이다. 이는 불필요한 성능 저하를 불러일으킨다.

이는 관계형 데이터를 객체지향 프로그래밍으로 옮겨오는 JPA의 특성상, 일어날 수 밖에 없는 일이다. 그렇기 때문에 JPA에는 지연로딩이 존재한다. 껍데기만 만들어두고 필요할 때만 실제 쿼리를 불러오는 식이다. 관계 설정 어노테이션의 fetch 파라미터로 LAZY를 설정해주면 된다.

```java
@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();
    
}

@Entity 
public class Member {

    @Id 
    @GeneratedValue
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID)
    private Team team;

}
```

Fetch Join을 사용하는 방법도 있다. 다음과 같은 JPQL 쿼리를 수행하면 지연로딩을 수행하는 것과 같은 효과를 낼 수 있다.
```java
Stirng jpql = "select m from Member m join fetch m.team"
List<Member> members = entityManager.createQuery(jpql, Member.class)
                                    .getResultList();
for (Member member: members) {
    System.out.println("username = " + member.getUsername() + ", " + 
                        "teamname: " + member.getTeam().name());
}
```

하지만 Fetch Join은 두 테이블 사이에만 적용이 가능하다. 여러 관계를 한꺼번에 Join 해야하는 경우, BatchSize를 이용할 수 있다.
```java
@Entity
class Member {

    @Id
    @GeneratedValue
    private Long id;
    
    // 이렇게 BatchSize를 5로 설정한다는 것은 최대 5개의 테이블을 조인하겠다는 의미이다.
    @org.hibernate.annotations.BatchSize(size = 5)
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();
    
}
```

이로써 다음과 같은 쿼리를 이행할 수 있다. 
```sql
SELECT * FROM ORDER WHERE MEMBER_ID IN (?, ?, ?, ?, ?)
```

---

# 영속성 전이

관계를 맺어 연결되어있는 두 엔티티 중 하나의 상태를 변경할 때, 다른 한쪽도 같이 변경되도록 설정하려면 CASCADE 속성을 사용하면 된다.

```java

@Entity
public class Parent {

    // Parent와 Child가 서로 참조하고있는데
    // cascade 타입을 ALL로 설정하면 Parent에 어떤 변화가 생길 때 관련된 Child들도 한꺼번에 변경된다.
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();

}

@Entity
public class Child {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
    
}

```

---

# 페이징 처리

페이지에 맞게 일정한 데이터 개수만큼 데이터를 내려주는 방식

일반적으로 SQL의 LIMIT, OFFSET 조건을 활용하여 구현할 수 있다.

```sql
// Review 테이블에서 0~4번 인덱스에 해당하는 엔티티를 반환한다.
SELECT * FROM Review LIMIT 5 OFFSET 0

// Reivew 테이블에서 pageNum번째 페이지의 아이템을 반환한다.
SELECT * FROM Review LIMIT 5 OFFSET (pageNum-1)*5
```

Spring Data JPA는 더 간단한 방법으로 페이징 처리를 할 수 있게 해준다.
```java
int age = 10;
int offset = 0;
int pageNum = 0

PageRequest pageRequest = PageRequest.of(offset, pageNum, Sort.by(Sort.Direction.DESC, "username"));
Page<Member> page = memberRepository.findByAge(age, pageRequest);
Page<MemberDto> dtoPage = page.map(m -> new MemberDto(...));
```

이 두가지 방법은 모두 오프셋 기반의 페이징 처리로, 새로운 데이터가 페이지 조회 중에 추가되면 데이터 중복을 막을 수 없다는 단접이 있다. 그리고 대부분의 RDBMS에 OFFSET 조건은 성능 저하를 유발한다. 이를 방지하려면 커서 기반의 페이징을 구현해야 하는데, 실제로는 구현이 꽤 복잡하다. 따라서 대부분의 경우, 오프셋을 활용하고 꼭 필요한 곳에서만 커서가 사용된다. 

---

# 객체지향쿼리

JPA는 관계형 데이터를 객체형 데이터로 변환하여 반환하는 API다. 하지만 관계형 데이터의 모든 기능을 객체로 옮겨오는 것은 불가능하다. 따라서 부분적으로 SQL과 비슷한 쿼리 언어를 도입하여 이를 보완할 필요가 있다. JPA는 이를 위해 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어를 제공하고 있다. JPQL은 엔티티 객체를 대상으로 하는 쿼리언어라고 할 수 있다.

```java
Stfing jpql = "select m from Member m where m.name like '%hello%'"
List<Member> result = entityManager.createeQuery(jpql, Member.class).getResultList();
```

## JPQL 기본 문법

```java
entityManager.createQuery("{JPQL Query}", {ClassName}.class)
```

- 엔티티, 속성은 대소문자를 구분 / 그 외 키워드들은 구분하지 않음
- 객체는 엔티티 이름을 사용(테이블 이름이 아님)
- 별칭은 필수(*를 못쓴다는 얘기. as는 생략 가능하다.)
- 집합, 정렬 함수 모두 사용 가능
- 반환 타입
    - TypedQuery: 반환 타입이 명확할 때: 
        - TypedQuery<Member> query = entityManager.createQuery("...", Member.class);
    - Query: 반환 타입이 명확하지 않을 때
        - Query query = entityManager.createQuery("...");
- 결과 조회 메서드
    - getResultList: 리스트 반환
        - List<Member> query = entityManager.createQuery("...", Member.class).getResultList();
    - getSingleResult: 단일 객체 반환
        - Member query = entityManager.createQuery("...", Member.class).getSingleResult();
- 파라미터 바인딩
    - 이름 기준
        - Member query = entityManager.createQuery("select m from Member m where m.username=username", Member.class)
            .setParameter(**"username"**, usernameParam)
            .getSingleResult();
    - 위치 기준 (수정 도중에 값이 변동될 수 있다)
        - Member query = entityManager.createQuery("select m from Member m where m.username=username", Member.class)
            .setParameter(**1**, usernameParam)
            .getSingleResult();
    

## QueryDSL

QueryDSL은 위와 같이 JPQL을 스트링으로 직접 쓰지 않고 자바 코드로 작성할 수 있게해주는 라이브러리이다. 코드로 작성하기 때문에 컴파일 시점에 오류를 찾을 수 있어 좋다. 주로 **동적 쿼리**를 작성할 때 사용된다.

- 동적쿼리: 특정 상황이나 조건에 따라 변경되는 쿼리. 예를 들어 숙박앱의 겁색 필터에서 여행지, 체크인&아웃, 인원 수 등은 입력 할 수도 있고 안할 수도 있다. 입력 여부에 따라 쿼리가 동적으로 변해야 한다.

## JDBC

별도의 라이브러리를 사용하지 않고 JDBC를 직접 사용할 수도 있다. 다만 이 경우, 적절한 시점에 플러시하는 것이 중요.
