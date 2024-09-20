package farmermod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import farmermod.cards.BaseCard;
import farmermod.character.TheFarmer;
import farmermod.util.CardInfo;

import static farmermod.FarmerMod.makeID;


public class Smallsword extends BaseCard {

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 3;

    private final static CardInfo cardInfo = new CardInfo(
            "Smallsword",
            0, // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, // ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ENEMY, // Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, // BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheFarmer.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public Smallsword() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(
                m,
                new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}
