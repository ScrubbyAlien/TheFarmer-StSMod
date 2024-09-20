package farmermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import farmermod.cards.BaseCard;
import farmermod.character.TheFarmer;
import farmermod.patches.FarmerTags;
import farmermod.powers.PlotPower;
import farmermod.util.CardInfo;

import static farmermod.FarmerMod.makeID;

public class Hoe extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "Hoe",
            1, // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, // ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, // Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, // BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheFarmer.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int PLOT = 1;
    private static final int UPG_PLOT = 1;



    public Hoe() {
        super(cardInfo);
        tags.add(FarmerTags.TOOL);
        setMagic(PLOT);
    }

    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void upgrade() {
        if (this.timesUpgraded < 5) {
            this.upgradeMagicNumber(UPG_PLOT);
            ++this.timesUpgraded;
            this.upgraded = true;
            this.name = cardStrings.NAME + "+" + this.timesUpgraded;
            this.initializeTitle();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PlotPower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Hoe();
    }


}
