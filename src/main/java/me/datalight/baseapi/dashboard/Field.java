package me.datalight.baseapi.dashboard;

public class Field {
    private String id;
    private String title;
    private String group;
    private String description;
    private String table;
    private boolean visible;

    String getId() {
        return id;
    }

    String getIdWith5F() {
        String original, with5F;
        original = getId();
        with5F = original.replaceAll("[_]", "_5F");
        return with5F;
    }

    void setId(String id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
