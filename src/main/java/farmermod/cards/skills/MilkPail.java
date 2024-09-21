package farmermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import farmermod.cards.BaseCard;
import farmermod.character.TheFarmer;
import farmermod.patches.FarmerTags;
import farmermod.powers.AnimalPower;
import farmermod.util.CardInfo;

import static farmermod.FarmerMod.makeID;

public class MilkPail extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "MilkPail",
            1, // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, // ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, // Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, // BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheFarmer.Enums.CARD_COLOR
    );

    private final static int BLOCK = 3;
    private final static int UPG_BLOCK = 2;


    public static final String ID = makeID(cardInfo.baseId);

    public MilkPail() {
        super(cardInfo);
        tags.add(FarmerTags.TOOL);
        setBlock(BLOCK, UPG_BLOCK);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // gain 3 block for every animal, if you have no animals gain 1 animal,

        boolean hasAnimals = p.getPower(AnimalPower.POWER_ID) != null;
        int numAnimals = 0;
        if (hasAnimals) numAnimals = p.getPower(AnimalPower.POWER_ID).amount;

        if (numAnimals == 0) {
            addToBot(new ApplyPowerAction(p, p, new AnimalPower(p, 1)));
        } else {
            addToBot(new GainBlockAction(p, BLOCK * numAnimals));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MilkPail();
    }

}
