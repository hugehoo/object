package chapter01.v2;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(Audience audience) {
        // original : buy() 메서드 내부로 bag 의 존재를 감췄다 : 캡슐화
        // ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));

        // updated : Audience 를 의존하게 됐지만,
        // TicketSeller 로 부턴 TicketOffice 의 data 인 amount 를 감췄으니 Trade-off
        ticketOffice.sellTicketTo(audience);

    }

}
