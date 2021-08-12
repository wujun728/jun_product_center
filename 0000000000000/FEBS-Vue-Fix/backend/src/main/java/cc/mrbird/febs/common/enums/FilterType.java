package cc.mrbird.febs.common.enums;

public enum FilterType {
    FIELD("field"),
    JOIN("join");
    private String type;
    FilterType(String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
