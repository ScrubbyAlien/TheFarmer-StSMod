package farmermod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import farmermod.patches.FarmerTags;

import static farmermod.FarmerMod.makeID;

public class PlotPower extends BasePower  {
    public static final String POWER_ID = makeID("Plot");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF; // Not technically, but shouldn't be removed by orange pellets.
    private static final boolean TURN_BASED = false;
    private static final boolean CAN_GO_NEGATIVE = true;

    public PlotPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.canGoNegative = CAN_GO_NEGATIVE;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(FarmerTags.SEED)) {
            this.reducePower(1);
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (this.amount < 0 && isPlayer) {
            this.flash();
            this.addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));
            this.amount = 0;
            this.updateDescription();
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
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
