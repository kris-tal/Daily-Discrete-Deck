package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.CardDesigns;
import dailydescretedeck.set.views.carddesignes.*;

import java.util.HashMap;
import java.util.Map;

public class CardDesignMap {
    private static final Map<CardDesigns, CardDesign> designMap = new HashMap<>();
    private static final Map<Class<? extends CardDesign>, CardDesigns> reverseDesignMap = new HashMap<>();

    static {
        designMap.put(CardDesigns.DEFAULT, new DefaultCardDesign());
        designMap.put(CardDesigns.BLUE, new BlueCardDesign());
        designMap.put(CardDesigns.EMO, new EmoCardDesign());
        designMap.put(CardDesigns.JH, new JHCardDesign());
        designMap.put(CardDesigns.RED, new RedCardDesign());
        designMap.put(CardDesigns.GALAXY, new GalaxyCardDesign());
        designMap.put(CardDesigns.ADVENTURE, new AdventureCardDesign());
        designMap.put(CardDesigns.FANTASY, new FantasyCardDesign());
        designMap.put(CardDesigns.WITCHER,new WitcherCardDesign());
        designMap.put(CardDesigns.ORANGE, new OrangeCardDesign());
        designMap.put(CardDesigns.BEACH, new BeachCardDesign());
    }

    static {
        reverseDesignMap.put(DefaultCardDesign.class, CardDesigns.DEFAULT);
        reverseDesignMap.put(BlueCardDesign.class, CardDesigns.BLUE);
        reverseDesignMap.put(EmoCardDesign.class, CardDesigns.EMO);
        reverseDesignMap.put(JHCardDesign.class, CardDesigns.JH);
        reverseDesignMap.put(RedCardDesign.class, CardDesigns.RED);
        reverseDesignMap.put(GalaxyCardDesign.class, CardDesigns.GALAXY);
        reverseDesignMap.put(AdventureCardDesign.class, CardDesigns.ADVENTURE);
        reverseDesignMap.put(FantasyCardDesign.class, CardDesigns.FANTASY);
        reverseDesignMap.put(WitcherCardDesign.class, CardDesigns.WITCHER);
        reverseDesignMap.put(OrangeCardDesign.class, CardDesigns.ORANGE);
    }

    public static CardDesigns getReverseInstance(CardDesign design) {
        return reverseDesignMap.get(design.getClass());
    }

    public static CardDesign getInstance(CardDesigns design) {
        return designMap.get(design);
    }
}
