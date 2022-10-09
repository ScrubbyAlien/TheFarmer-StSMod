package farmermod.relics.farmer;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import farmermod.character.TheFarmer;
import farmermod.powers.PlotPower;
import farmermod.relics.BaseRelic;

import static farmermod.FarmerMod.makeID;

public class GrandpasFarm extends BaseRelic {

    private static final String NAME = "GrandpasFarm"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    private static final int PLOT = 1;

    public GrandpasFarm() {
        super(ID, NAME, TheFarmer.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlotPower(AbstractDungeon.player, PLOT), PLOT));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0], PLOT);
    }

}
