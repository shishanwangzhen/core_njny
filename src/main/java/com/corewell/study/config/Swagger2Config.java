package com.corewell.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2023/02/23/9:29
 * @Description:
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径,控制器类包
                .apis(RequestHandlerSelectors.basePackage("com.corewell.study.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(defaultHeader());
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("智慧农业物联网实训云平台API接口文档")
                //创建人
                .contact(new Contact("十山", null,
                        null))
                //版本号
                .version("1.0")
                //描述
                .description("系统API描述:云平台实现的功能就是以实验为中心，管理学生，管理实训台设备。以项目组的方式进行分组管理。")
                .build();
    }

    private static List<Parameter> defaultHeader() {
       /* ParameterBuilder appType = new ParameterBuilder();
        appType.name("app-type").description("应用类型").modelRef(new ModelRef("string")).parameterType("header").required(false).build();*/
        ParameterBuilder appToken = new ParameterBuilder();
        appToken.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        List<Parameter> pars = new ArrayList<>();
        pars.add(appToken.build());
        //pars.add(appType.build());
        return pars;
    }

}
