package farmermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import farmermod.cards.BaseCard;
import farmermod.character.TheFarmer;
import farmermod.patches.FarmerTags;
import farmermod.powers.ClintPower;
import farmermod.util.CardInfo;

import static farmermod.FarmerMod.makeID;

public class Clint extends BaseCard {
    private static final CardInfo info = new CardInfo(
            Clint.class.getSimpleName(),
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            TheFarmer.Enums.CARD_COLOR
    );
    public static final String ID = makeID(info.baseId);


    public Clint() {
        super(info);
        tags.add(FarmerTags.VILLAGER);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ClintPower(p, -1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Clint();
    }
}
