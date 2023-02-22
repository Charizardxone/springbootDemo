package com.fc.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "user")
public class SysUserEntity implements Serializable {
    /** ID */
    @Id
    private String id;

    /** 用户名 */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String username;

    /** 密码 */
    @Field(type = FieldType.Text)
    private String password;

    /** 电话号码 */
    private String phone;

    /** 电子邮件地址 */
    private String email;

    /** 昵称 */
    private String nickName;

    private String avatarUrl;

    /** 创建时间 */
    private Date createTime;

    /** 最后一次修改时间 */
    private Date lastUpdateTime;

    private String token;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", nickName=").append(nickName);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
