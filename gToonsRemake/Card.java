/**
 * Created by bokense on 24-May-16.
 */
public class Card {


    private String name;
    private String color;
    public int baseValue;
    public int currentValue;
    private String[] effectText;
    private String archetype;
    private String subtype;


    public Card(String cardType){

        this.name = cardType;

        this.effectText = new String[4];

        switch (cardType){
            case "Tree":


                this.color = "green";
                this.baseValue = 4;
                this.effectText[0] = "+1 for each green";
                this.effectText[1] = "card";
                this.archetype = "forestry";
                this.subtype = "plant";
                break;
            case "Lumberjack":
                this.color = "red";
                this.baseValue = 6;
                this.effectText[0] = "+3 for each ";
                this.effectText[1] = "tree";
                this.archetype = "forestry";
                this.subtype = "human";
                break;

            case "null":


                break;
        }
        currentValue = baseValue;


    }



    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String[] getEffectText() {
        return effectText;
    }

    public void setEffectText(String[] effectText) {
        this.effectText = effectText;
    }

}
