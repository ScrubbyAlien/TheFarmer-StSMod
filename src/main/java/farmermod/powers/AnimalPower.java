package farmermod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static farmermod.FarmerMod.makeID;

public class AnimalPower extends BasePower {
    public static final String POWER_ID = makeID("Animal");
    private static final PowerType TYPE = PowerType.BUFF; // Not technically, but shouldn't be removed by orange pellets.
    private static final boolean TURN_BASED = false;
    private static final boolean CAN_GO_NEGATIVE = false;

    public AnimalPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.canGoNegative = CAN_GO_NEGATIVE;
    }

    public void stackPower(int stackAmount) {
        this.flashWithoutSound();
        this.amount += stackAmount;
        this.updateDescription();
    }

    public void reducePower(int reduceAmount) {
        this.flashWithoutSound();
        this.amount -= reduceAmount;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
