package me.datalight.baseapi.handbook;

public class Coin {
    private String id;
    private String symbol;
    private String name;
    private String logo;
    private String rank;
    private transient String short_description;
    private transient String head_picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getHead_picture() {
        return head_picture;
    }
}
