package com.neo.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutoDataSourceConfig {
    private String jdbcUrl;
    private String jdbcDriverClassName;
    private String jdbcUserName;
    private String jdbcPassword;
}
