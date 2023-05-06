package chapter02.policy.v2;

import java.time.Duration;

import chapter02.Money;

public class Customer {

    public static void main(String[] args) {
        Movie avatar = new Movie("avatar",
            Duration.ofMinutes(120),
            Money.wons(1000),
            new AmountDiscountPolicy(Money.wons(1000)));

        // when changing the discount policy anfter initilization
        avatar.changeDiscountPolicy(new PercentDiscountPolicy(20));
    }
}
