package chapter02.policy.v1;

import chapter02.Money;
import chapter02.Screening;
import chapter02.condition.DiscountCondition;
import chapter02.policy.v2.DefaultDiscountPolicy;

public class AmountDiscountPolicy extends DiscountPolicy {
    private final Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
