package com.fc.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuanRecordDetailEntity implements Serializable {
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 最后一次修改时间 */
    private Date lastUpdateTime;

    private String recordId;

    private Date getTime;

    private String cardName;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", recordId=").append(recordId);
        sb.append(", getTime=").append(getTime);
        sb.append(", cardName=").append(cardName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}