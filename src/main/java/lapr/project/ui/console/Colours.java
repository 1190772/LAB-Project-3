package lapr.project.ui.console;

public enum Colours {
    BLACK("#000000"), GREY("#808080"), BROWN("#964B00"), RED("#FF0000"),
    ORANGE("FFA500"), YELLOW("#FFFF00"), GREEN("#00FF00"), CYAN("#00FFFF"),
    BLUE("#0000FF"), PURPLE("#800080"), WHITE("#FFFFFF"), PINK("#FFC0CB"),
    LIME("#BFFF00"), COBALT("#0041AB"), BRICK("#8B4F39");

    private String hexCode;

    Colours(String hexCode) {
        this.hexCode = hexCode;

    }

    public static Colours ordinal(int index){
        return Colours.values()[index];
    }
}
