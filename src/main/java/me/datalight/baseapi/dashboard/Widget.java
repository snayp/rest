package me.datalight.baseapi.dashboard;

import java.util.List;

public class Widget {
    private Integer id;
    private String default_key;
    private String control;
    private String picture;
    private String title;
    private String description;
    private String visible;
    private String position;
    private String time_interval;
    private String layers_meta;
    private String filters;
    private List<String> visible_fields;
    private List<Layer> layers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefault_key() {
        return default_key;
    }

    public void setDefault_key(String default_key) {
        this.default_key = default_key;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTime_interval() {
        return time_interval;
    }

    public void setTime_interval(String time_interval) {
        this.time_interval = time_interval;
    }

    public String getLayers_meta() {
        return layers_meta;
    }

    public void setLayers_meta(String layers_meta) {
        this.layers_meta = layers_meta;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public List<String> getVisible_fields() {
        return visible_fields;
    }

    public void setVisible_fields(List<String> visible_fields) {
        this.visible_fields = visible_fields;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}
