package farmermod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import farmermod.patches.FarmerTags;

import static farmermod.FarmerMod.makeID;

public class ClintPower extends BasePower {
    public static final String POWER_ID = makeID(ClintPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF; // Not technically, but shouldn't be removed by orange pellets.
    private static final boolean TURN_BASED = false;
    private static final boolean CAN_GO_NEGATIVE = false;

    public ClintPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.canGoNegative = CAN_GO_NEGATIVE;
        this.description = DESCRIPTIONS[0];
    }

    public void stackPower(int stackAmount) {
        if (this.amount == 0) this.flashWithoutSound();
        this.amount = -1;
    }

    public void onCardDraw(AbstractCard card) {
        if (card.hasTag(FarmerTags.TOOL)) {
            card.upgrade();
        }
    }


}
