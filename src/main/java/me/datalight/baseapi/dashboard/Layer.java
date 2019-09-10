package me.datalight.baseapi.dashboard;

import java.util.List;

public class Layer {
    private String id;
    private String diagram;
    private String outer_key;
    private String gradient;
    private String y_axis_enabled;
    private String order_by;
    private String get_other;
    private String field_order;
    private List<String> columns;
    private List<String> rows;
    private List<String> metrics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public String getOuter_key() {
        return outer_key;
    }

    public void setOuter_key(String outer_key) {
        this.outer_key = outer_key;
    }

    public String getGradient() {
        return gradient;
    }

    public void setGradient(String gradient) {
        this.gradient = gradient;
    }

    public String getY_axis_enabled() {
        return y_axis_enabled;
    }

    public void setY_axis_enabled(String y_axis_enabled) {
        this.y_axis_enabled = y_axis_enabled;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public String getGet_other() {
        return get_other;
    }

    public void setGet_other(String get_other) {
        this.get_other = get_other;
    }

    public String getField_order() {
        return field_order;
    }

    public void setField_order(String field_order) {
        this.field_order = field_order;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public List<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<String> metrics) {
        this.metrics = metrics;
    }
}
