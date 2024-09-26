package farmermod.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static farmermod.FarmerMod.makeID;

public class AnimalPower extends BasePower {
    public static final String POWER_ID = makeID("AnimalPower");
    private static final PowerType TYPE = PowerType.BUFF; // Not technically, but shouldn't be removed by orange pellets.
    private static final boolean TURN_BASED = false;
    private static final boolean CAN_GO_NEGATIVE = false;

    public AnimalPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.canGoNegative = CAN_GO_NEGATIVE;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int difference = this.amount - AbstractDungeon.player.energy.energy;
            if (difference > 0) { // get max energy
                addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, difference));
            }
        }
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
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }
}
