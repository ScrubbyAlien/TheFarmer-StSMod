package farmermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import farmermod.cards.BaseCard;
import farmermod.character.TheFarmer;
import farmermod.patches.FarmerTags;
import farmermod.util.CardInfo;

import static farmermod.FarmerMod.makeID;

public class Potato extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "Potato",
            1, // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, // ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, // Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, // BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheFarmer.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public static final int BLOCK = 6;
    public static final int MAGIC = 3;
    public static final int UPG_MAGIC = 1;

    public Potato() {
        super(cardInfo);
        tags.add(FarmerTags.POTATO);
        setBlock(BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true, true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        int numberOfPotatoes = 0;
        for (AbstractCard card : p.hand.group) {
            if (card.hasTag(FarmerTags.POTATO)) numberOfPotatoes++;
        }
        for (int i = 0; i < numberOfPotatoes - 1; i++) { // -1 so we don't count the played card.
            addToBot(new GainBlockAction(p, p, magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Potato();
    }

    // TODO: add extended description so you can see how much block you will actually get.

}
