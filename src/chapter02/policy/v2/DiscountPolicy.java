package chapter02.policy.v2;

import chapter02.Money;
import chapter02.Screening;

public interface DiscountPolicy {
    public Money calculateDiscountAmount(Screening screening);
}
