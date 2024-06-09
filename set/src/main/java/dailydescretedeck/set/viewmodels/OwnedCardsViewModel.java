package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.views.carddesignes.BlueCardDesign;
import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;
import dailydescretedeck.set.views.carddesignes.JHCardDesign;
import dailydescretedeck.set.views.carddesignes.RedCardDesign;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class OwnedCardsViewModel {
    private ArrayList<CardDesign> ownedCardDesignes;

    public OwnedCardsViewModel() {
        ownedCardDesignes = new ArrayList<>(List.of(new DefaultCardDesign(),
                new JHCardDesign(),
                new RedCardDesign(),
                new BlueCardDesign()));
        //wczytaj je z pliku
    }

    public CardDesign getDesign(int index) {
        return ownedCardDesignes.get(index);
    }

    public int getDesignsSize() {
        return ownedCardDesignes.size();
    }

    public ArrayList<CardDesign> getOwnedCardDesigns() {
        return ownedCardDesignes;
    }

    public ArrayList<Dots> getFullDots() {
        return new ArrayList<>(List.of(Dots.A1, Dots.A2, Dots.B1, Dots.B2, Dots.C1, Dots.C2));
    }

}
