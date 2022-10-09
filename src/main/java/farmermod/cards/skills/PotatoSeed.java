package farmermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import farmermod.cards.BaseCard;
import farmermod.character.TheFarmer;
import farmermod.patches.FarmerTags;
import farmermod.util.CardInfo;

import static farmermod.FarmerMod.makeID;

@SuppressWarnings("unused")
public class PotatoSeed extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "PotatoSeed",
            0, // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, // ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, // Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, // BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheFarmer.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    // declare static values

    public static final int MAGIC = 3;

    public PotatoSeed() {
        super(cardInfo, true);
        tags.add(FarmerTags.SEED);
        cardsToPreview = new Potato();
        setMagic(MAGIC);
        // set values
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new MakeTempCardInDrawPileAction(cardsToPreview, 1, true, true));
        }
    }

    public void upgrade() {
        super.upgrade();
        cardsToPreview.upgrade();
    }

    @Override
    public AbstractCard makeCopy() {
        return new PotatoSeed();
    }

}
