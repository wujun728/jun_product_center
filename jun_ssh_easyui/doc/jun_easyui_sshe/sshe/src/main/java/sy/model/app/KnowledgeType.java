package sy.model.app;

import java.io.Serializable;

public class KnowledgeType implements Serializable {
    private int typeId;

    private String typeName;

    private static final long serialVersionUID = 1L;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}