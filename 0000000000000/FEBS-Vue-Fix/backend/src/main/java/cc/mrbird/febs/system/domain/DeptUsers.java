package cc.mrbird.febs.system.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class DeptUsers implements Serializable {
    private static final long serialVersionUID = -5615190273698747063L;
    private Long deptId;
    private String userIds;
}
