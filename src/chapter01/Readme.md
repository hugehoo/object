# Chapter01
개발자는 구체적인 코드를 만지며 손을 더럽힐 때 가장 많은 것을 얻어가는 존재

변경이 가능한 코드를 만들어야 하는 이유
1. 코드는 생명주기 동안 대부분 변경되기 마련이다.
2. 변경된 코드로 인해 발생할 수 있는 버그에 대처하기 위해서라도, 코드는 변경에 유연해야 한다.

의존성의 뜻은 어떤 객체가 변경될 때 그 객체에게 의존하는 다른 객체도 함께 변경될 수 있다는 가능성을 내포한다.
그럼 의존성이 Zero 인 상태가 가장 바람직한 것일까? 그건 정답이 아니며 객체지향 설계에서 Zero-Dependency 가 가능할 리도 없다.
객체지향 설계란 객체간 서로 의존하며 협력하는 객체들의 공동체를 구축하는 것이다.
즉 애플리케이션의 기능을 구현하는데 필요한 최소한의 의존성만 유지하고 불필요한 의존성을 제거할 수 있는 설계가 필요하다.

### 결합도
의존성의 정도를 나타내는 말로, 의존성이 과할 경우 결합도가 높다고 말할 수 있다.
결합도는 의존성과 상관관계를 띄기 때문에, 결합도 역시 변경과 관련이 있다.
두 객체 사이의 결합도가 높다면 변경에 유연하지 못할 가능성이 높다. 

예제에서는 Theater 가 Audience 의 데이터인 Bag 에 직접 접근하는 모습을 볼 수 있는데, 이는 두 객체간의 결합도가 높은 상태로 볼 수 있다.
Theater 가 audience.bag 에 쉽게 접근 가능하다는 것은, 너무 많은 Audience 의 구현을 알고 있다는 의미로 bag 이 수정되면 Theater 도 수정될 가능성이 높다.

```java
public class Theater {
    ...
    public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {  // (1)
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();  // (2)
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            Long ticketFee = ticket.getFee();
            ...
        }
    }
}
```

enter 메서드의 구현은 다른 객체의 세부사항을 과하게 잘 알고 있다. 이러한 객체 내부의 세부 사항을 감추는 것을 `캡슐화` 라 부른다.
캡슐화의 목적은 변경하기 쉬운 객체를 만드는 것이다. 
앞서 설명했듯이 과한 결합도는 변경하기 어려운 객체를 만든다. 반대로 말해 결합도를 떨어뜨리면 변경하기 쉬운 객체를 만들 수 있다.
즉 캡슐화를 통해 객체간의 결합도를 낮출 수 있다는 의미이다.
캡슐화를 통해 객체 내부의 접근을 제한하면 객체 사이의 결합도를 낮출 수 있기 때문에 설계를 유연하게 변경할 수 있다.

이렇듯 캡슐화는 물리적, 논리적 세부 구현 사항을 감추는데, 이는 결국 구현 사항을 알맞은 객체 내부로 옮긴 형태이다. 즉 자기 자신의 문제를 스스로 해결하도록 코드를 변경한 것. 


핵심) 세부 구현은 각 객체가 책임지도록 캡슐화 시킨 후 객체간의 상호작용은 메시지를 통해 이루어지게 만든다.
세부 구현을 각 객체가 책임지는 것은, `객체 스스로 자신의 데이터를 책임진다`는 의미이기도 하다.

기존 V1의 문제는 모든 처리(process)가 하나의 클래스에 위치하고, 나머지 클래스는 단지 데이터를 제공하는 역할만 했을 뿐이다.
V2는 데이터와 프로세스가 동일한 모듈에 위치하도록 개선됐다. 각 객체의 데이터를 중심으로 프로세스가 이동, 분배된 것이다.
이렇듯 객체지향 설계에서는 제어 흐름이 각 객체에 적절하게 분사돼 있음을 알 수 있다. 데이터와 데이터를 사용하는 프로세스가 동일한 모듈에 위치한다면 객체지향 프로그래밍 방식을 따르고 있을 가능성이 높다.

`책임` : 객체가 자신을 스스로 책임진다는 뜻은, 자신의 data 를 처리해야 하는 process 를 자기 자신이 담당한다는 의미.
의존성은 없애는 것이 아니라 통제하는 것이다. 

어떤 객체에서 다른 객체를 의존하여 프로세스를 처리하고 있을 때, 해당 객체의 세부 구현을 너무 많이 알고 있다면 적절한 캡슐화가 필요한 시점일 수 있다.
의존하고 있는 객체의 데이터를 감춰 그 존재를 모르게 할 수 있다면 설계를 변경할 필요가 있다.  즉 의존 객체 내부에서 해당 프로세스를 처리하도록 변경한다.

### 설계와 구현
설계를 구현과 떨어뜨려 논의하는 것은 불가능하다. 설계는 코드를 작성하는 매 순간 코드를 어떻게 배치할 것인지 결정하는 과정에서 나온다. 
설계는 코드 작성의 일부이며 코드를 작성하지 않고서는 검증할 수 없다.

객체간 상호작용을 위해선 메시지가 필요하고, 메시지는 두 객체간 결합으로 만들 수 있다. 즉 의존성은 필연적이고 이를 잘 통제할 수 있느냐가 객체지향의 관건이다.
