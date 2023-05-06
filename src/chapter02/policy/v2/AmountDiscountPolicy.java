package chapter02.policy.v2;

import chapter02.Money;
import chapter02.Screening;
import chapter02.condition.DiscountCondition;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {
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
