package com.lntu.digitalhuman.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "human_data")
public class HumanData {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String modelFileId;
    private String modelFileName;
    
    // 添加创建时间和更新时间字段
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}