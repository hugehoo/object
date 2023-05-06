package chapter02.condition;

import chapter02.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
