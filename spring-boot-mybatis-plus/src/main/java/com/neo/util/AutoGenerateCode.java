/**
 * pxys
 * Copyright (C) 2017-2019 All Rights Reserved.
 */
package com.neo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AutoGenerateCode
 * @Description:
 * @Author: Monta
 * @Date: 2019/11/11 下午4:08
 **/
@Component
public class AutoGenerateCode {

    private static Logger logger = LogManager.getLogger(AutoGenerateCode.class);

    public static void main(String[] args) {
        /**
         * 根路径，我这里先直接放到此项目中。可以根据自己需要修改制定
         */
        String basePath = "D:\\workspace\\codes\\spring-boot-examples\\spring-boot-mybatis-plus";
        String parentPackageName = "com.neo";
        String moduleName = "demo";
        String[] tableNames = new String[]{"table_name_demo"};
        String[] tablePres = new String[]{""};
        String jdbcHost = "localhost";
        String jdbcPort = "3306";
        String jdbcDatabase = "demo";
        String jdbcUrl = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=false&autoReconnect=true&serverTimezone=Asia/Shanghai",
                jdbcHost, jdbcPort, jdbcDatabase);
        System.out.println(jdbcUrl);
        AutoDataSourceConfig autoDataSourceConfig = AutoDataSourceConfig.builder()
                .jdbcDriverClassName("com.mysql.jdbc.Driver")
                .jdbcPassword("123456")
                .jdbcUserName("root")
                .jdbcUrl(jdbcUrl)
                .build();
        String outputDir = String.format("%s/src/main/java", basePath);
        new AutoGenerateCode().generateCode(outputDir, parentPackageName,
                moduleName, autoDataSourceConfig, tablePres, tableNames);
    }


    /**
     * 生成代码
     *
     * @param outputDir         输出目录
     * @param parentPackageName 父级包
     * @param moduleName        模块名称
     * @param dataSourceConfig  数据源配置
     * @param tablePres         表前缀
     * @param tableNames        待生成表名
     */
    private void generateCode(String outputDir, String parentPackageName,
                              String moduleName, AutoDataSourceConfig dataSourceConfig, String[] tablePres, String[] tableNames) {
        try {
            AutoGenerator autoGenerator = new AutoGenerator();
            autoGenerator.setGlobalConfig(initGlobConfig(outputDir))
                    .setDataSource(initDataSource(dataSourceConfig))
                    .setStrategy(initStrategyConfig(tablePres, tableNames))
                    .setPackageInfo(initPackage(parentPackageName, moduleName))
                    .setTemplate(initTemplateConfig())
                    .execute();
            logger.error(">>>>>>> Generate Code Completed... >>>>>>>");
        } catch (Exception e) {
            logger.error("Generate Code failed:", e);
            throw new RuntimeException(String.format("Generate Code failed"));
        }
    }

    /**
     * 全局设置配置
     *
     * @param outputDir 生成文件输出根目录
     * @return GlobalConfig
     */
    private GlobalConfig initGlobConfig(String outputDir) {
        return new GlobalConfig()
                .setOutputDir(outputDir)
                /**
                 * 方法都比较见名知意了，就不做赘述
                 */
                .setFileOverride(false)
                .setOpen(false)
                .setEnableCache(false)
                .setKotlin(false)
                .setActiveRecord(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                /**
                 * 以下个人系统的类后缀，可根据自己需要调整
                 */
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setAuthor("monta")
                .setIdType(IdType.ID_WORKER);
    }

    /**
     * 设置数据源信息
     *
     * @param dataSourceConfig 数据源配置
     * @return DataSourceConfig
     */
    private DataSourceConfig initDataSource(AutoDataSourceConfig dataSourceConfig) {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert() {
                    /**
                     * 自定义部分数据库类型对应转换的Java类型，根据个人需要设定
                     */
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        if (fieldType.toLowerCase().contains("tinyint")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().contains("timestamp")) {
                            return DbColumnType.DATE;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
                .setDriverName(dataSourceConfig.getJdbcDriverClassName())
                .setUrl(dataSourceConfig.getJdbcUrl())
                .setUsername(dataSourceConfig.getJdbcUserName())
                .setPassword(dataSourceConfig.getJdbcPassword());
    }

    /**
     * 设置生成策略
     *
     * @param tablePre   表前缀
     * @param tableNames 待生成表列表
     * @return StrategyConfig
     */
    private StrategyConfig initStrategyConfig(String[] tablePre, String[] tableNames) {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix(tablePre)
                .setControllerMappingHyphenStyle(true)
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityLombokModel(true)
                .setInclude(tableNames)
                .setRestControllerStyle(false);
    }

    /**
     * 设置包配置信息
     *
     * @param parentPackageName 父级包
     * @param moduleName        模块名称
     * @return PackageConfig
     */
    private PackageConfig initPackage(String parentPackageName, String moduleName) {
        logger.info(moduleName);
        PackageConfig packageConfig = new PackageConfig()
                .setParent(parentPackageName)
                .setEntity("entity")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper")
                .setXml("mapper.xml")
                .setController("controller");
        if (StringUtils.isNotEmpty(moduleName)) {
            packageConfig.setModuleName(moduleName);
        }
        return packageConfig;
    }

    /**
     * 指定生成代码模板文件路径【可以不指定，使用mp默认的】
     *
     * @return TemplateConfig
     */
    private TemplateConfig initTemplateConfig() {
        return new TemplateConfig();
//        .setController("/templates/mybatis/controller.java.vm");
        /**
         * 这里暂时不自定义模板，若有需要可自行修改
         */
//                .setController("/templates/mybatis/controller.java.vm")
//                .setService("/templates/mybatis/service.java.vm")
//                .setEntity("/templates/mybatis/entity.java.vm")
//                .setServiceImpl("/templates/mybatis/serviceImpl.java.vm")
//                .setXml("/templates/mybatis/mapper.xml.vm")
//                .setMapper("/templates/mybatis/mapper.java.vm");

    }
}