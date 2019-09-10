package me.datalight.baseapi.api;

import java.util.ArrayList;

public class Object {
    private String id;
    private String title;
    private String group;
    private String field_type;
    private String data_type;
    private String unit_measure;
    private String symbol;
    private boolean can_be_null;
    private String description;
    private String period;
    private String meta;
    private ArrayList<String> allow_filters;
    private ArrayList<String> table_periods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getField_type() {
        return field_type;
    }

    public void setField_type(String field_type) {
        this.field_type = field_type;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getUnit_measure() {
        return unit_measure;
    }

    public void setUnit_measure(String unit_measure) {
        this.unit_measure = unit_measure;
    }


    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isCan_be_null() {
        return can_be_null;
    }

    public void setCan_be_null(boolean can_be_null) {
        this.can_be_null = can_be_null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public ArrayList<String> getAllow_filters() {
        return allow_filters;
    }

    public void setAllow_filters(ArrayList<String> allow_filters) {
        this.allow_filters = allow_filters;
    }

    public ArrayList<String> getTable_periods() {
        return table_periods;
    }

    public void setTable_periods(ArrayList<String> table_periods) {
        this.table_periods = table_periods;
    }
}
