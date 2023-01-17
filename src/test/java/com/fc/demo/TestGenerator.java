package com.fc.demo;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yfc
 * @date 2022/9/9 15:48
 */
public class TestGenerator {

    private static File configFile;
    static {
        String path = "D:\\dev\\fcPro\\demo\\src\\test\\resources\\generatorConfig.xml";
        System.out.println(path);
        configFile = new File("src\\test\\resources\\generatorConfig.xml");
    }

    public static void main(String[] args)
            throws IOException,
            XMLParserException,
            InvalidConfigurationException,
            SQLException,
            InterruptedException {

        if(!configFile.exists()) {
            System.out.println("配置文件不存在");
            return;
        }

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
