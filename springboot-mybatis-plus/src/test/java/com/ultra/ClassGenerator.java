package com.ultra;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class ClassGenerator {

    /**
     * 数据库配置
     */
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/springboot?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String TABLE_PREFIX = "spring_";
    //"spring_user", "spring_role", "spring_user_role", "spring_resource", "spring_role_resource"
    private static final String[] TABLES = {"spring_user"};

    /**
     * 模板风格
     */
    private static final String PACKAGE_NAME = "com.ultra";
    private static final String ENTITY_VM = "/templates/entity.java.vm";
    private static final String CONTROLLER_VM = "/templates/controller.java.vm";
    private static final String SUPERD_CONTROLLER_CLASS = "com.ultra.web.BaseController";
    // user -> UserService, 设置成true: user -> IUserService
    private static final boolean SERVICE_NAME_START_WITH_I = false;

    private static final String CODE_OUTPUT_DIR = "E:\\codes";

    @Test
    public void generateCode() {

        DataSourceConfig dataSourceConfig = initDataSourceConfig();
        StrategyConfig strategyConfig = initStrategyConfig();
        GlobalConfig config = initGlobalConfig();
        PackageConfig packageConfig = initPackageConfig();
        TemplateConfig templateConfig = initTemplateConfig();
        new AutoGenerator().setGlobalConfig(config).setDataSource(dataSourceConfig).setStrategy(strategyConfig)
                .setPackageInfo(packageConfig).setTemplate(templateConfig).execute();
    }

    /**
     * 模板设置
     *
     * @return
     */
    private TemplateConfig initTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        if (StringUtils.isNotBlank(ENTITY_VM)) {
            templateConfig.setEntity(ENTITY_VM);
        }
        if (StringUtils.isNotBlank(CONTROLLER_VM)) {
            templateConfig.setController(CONTROLLER_VM);
        }
        return templateConfig;
    }

    /**
     * 包路径配置
     *
     * @return
     */
    private PackageConfig initPackageConfig() {
        return new PackageConfig().setParent(PACKAGE_NAME).setController("web").setService("service")
                .setServiceImpl("service.impl").setMapper("dao").setXml("mapper").setEntity("dao.entity");
    }

    /**
     * mapperxml输出配置
     *
     * @return
     */
    private GlobalConfig initGlobalConfig() {
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false).setEnableCache(false).setOutputDir(CODE_OUTPUT_DIR).setFileOverride(true)
                .setSwagger2(true).setBaseResultMap(true).setBaseColumnList(true);
        if (!SERVICE_NAME_START_WITH_I) {
            config.setServiceName("%sService");
        }
        return config;
    }

    /**
     * 数据库表转对象策略配置
     *
     * @return
     */
    private StrategyConfig initStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true).setEntityLombokModel(true).setRestControllerStyle(true)
                .setTablePrefix(TABLE_PREFIX).setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel).setInclude(TABLES)
                .setSuperControllerClass(SUPERD_CONTROLLER_CLASS);
        return strategyConfig;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    private DataSourceConfig initDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL).setUrl(DB_URL).setUsername(DB_USERNAME).setPassword(DB_PASSWORD)
                .setDriverName(DB_DRIVER);
        return dataSourceConfig;
    }

}