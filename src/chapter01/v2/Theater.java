package chapter01.v2;

public class Theater {

    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    // audience 객체가 변한다고 해도 그에 따른 책임은 TicketSeller 가 질뿐, Theater 는 의존하지 않는다.
    public void enter(Audience audience) {
        ticketSeller.sellTo(audience);
    }
}
