package com.library.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
//@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("b");
    }

//    @Bean
//    public Docket studentDocket(Environment environment){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .enable(true)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.library.manage.controller.student"))
//                .paths(PathSelectors.ant("/swagger/**"))
//                .build();
//    }
//
//    private ApiInfo apiInfo(){
//        return new ApiInfo("LibManage Api",
//                "Java course homework",
//                "version 1.0",
//                "urn:tos",
//                new Contact("cgd", "", "@qq.com"),//作者信息
//                "Apache 2.0",
//                "http://www.apache.org/licenses/LICENSE-2.0",
//                new ArrayList());
//    }

}
