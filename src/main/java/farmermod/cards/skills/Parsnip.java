package farmermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import farmermod.cards.BaseCard;
import farmermod.character.TheFarmer;
import farmermod.util.CardInfo;

import static farmermod.FarmerMod.seedCards;
import static farmermod.FarmerMod.makeID;

public class Parsnip extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "Parsnip",
            1, // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, // ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, // Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, // BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheFarmer.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public static final int MAGIC = 1;
    public static final int UPG_MAGIC = 1;

    public Parsnip() {
        super(cardInfo);

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true, true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));

        // shuffle a random common seed card into the draw pile
        AbstractCard randomCommonSeedCard = seedCards.getRandomCard(true, CardRarity.COMMON);
        addToBot(new MakeTempCardInDrawPileAction(randomCommonSeedCard, 1, true, true));

        addToBot(new DrawCardAction(magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Parsnip();
    }

}
