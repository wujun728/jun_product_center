package cc.mrbird.febs.common.handler;

public enum ResponseStat {
    SUCCESS("success"),
    ERROR("error");
    private String text;
    ResponseStat(String text){
        this.text=text;
    }
    public String getText() {
        return text;
    }
}
