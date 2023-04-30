package chapter01.v1;

public class Theater {

    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    // Theater 가 audience.bag 에 쉽게 접근 가능 => 너무 많은 구현을 알고 있다
    // bag 이 수정되면 Theater 도 수정될 가능성이 높다
    public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            Long ticketFee = ticket.getFee();

            audience.getBag().minusAmount(ticketFee);
            ticketSeller.getTicketOffice().plusAmount(ticketFee);
            audience.getBag().setTicket(ticket);
        }
    }
}
