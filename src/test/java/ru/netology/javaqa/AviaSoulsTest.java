package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {
    AviaSouls AviaSouls = new AviaSouls();
    TicketTimeComparator ticketComparator = new TicketTimeComparator();
    Ticket ticket_1 = new Ticket("Самара", "Москва", 9_700, 21_00, 22_30);
    Ticket ticket_2 = new Ticket("Самара", "Сургут", 17_400, 1_00, 3_20);
    Ticket ticket_3 = new Ticket("Москва", "Нижневартовск", 15_300, 9_00, 12_20);
    Ticket ticket_4 = new Ticket("Санкт-Петербург", "Самара", 5_500, 12_00, 13_30);
    Ticket ticket_5 = new Ticket("Санкт-Петербург", "Самара", 9_700, 16_00, 18_10);
    Ticket ticket_6 = new Ticket("Санкт-Петербург", "Самара", 23_600, 13_30, 20_50);

    @BeforeEach
    public void setup() {
        AviaSouls.add(ticket_1);
        AviaSouls.add(ticket_2);
        AviaSouls.add(ticket_3);
        AviaSouls.add(ticket_4);
        AviaSouls.add(ticket_5);
        AviaSouls.add(ticket_6);
    }

    @Test
    public void shouldFindAllTickets() {
        Ticket[] expected = {ticket_1, ticket_2, ticket_3, ticket_4, ticket_5, ticket_6};
        Ticket[] actual = AviaSouls.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeComparedWithTheMinimumCost() {
        int expected = -1;
        int actual = ticket_1.compareTo(ticket_2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsAtTheSamePrice() {
        int expected = 0;
        int actual = ticket_5.compareTo(ticket_1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeComparedWithTheMaximumCost() {
        int expected = 1;
        int actual = ticket_6.compareTo(ticket_2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareTheMinimumFlightTime() {
        int expected = -1;
        int actual = ticketComparator.compare(ticket_4, ticket_2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeComparedSameFlightTime () {
        int expected = 0;
        int actual = ticketComparator.compare(ticket_4, ticket_1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareTheMaximumFlightTime() {
        int expected = 1;
        int actual = ticketComparator.compare(ticket_6, ticket_5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void youShouldFindTicketsWithTheSameDirections() {
        Ticket[] expected = {ticket_4, ticket_5, ticket_6};
        Ticket[] actual = AviaSouls.search("Санкт-Петербург", "Самара");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldLookForAOne_WayTicket() {
        Ticket[] expected = {ticket_3};
        Ticket[] actual = AviaSouls.search("Москва", "Нижневартовск");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeSearchedAndSortedInAscendingOrder() {
        Ticket[] expected = {ticket_4, ticket_5, ticket_6};
        Ticket[] actual = AviaSouls.searchAndSortBy("Санкт-Петербург", "Самара", ticketComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
