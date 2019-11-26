package com.project.config;

import com.project.beans.DBUtil;
import com.project.entites.Comments;
import com.project.entites.Posts;
import com.project.entites.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"com.project"})
public class BeansConfig {
    @Bean
    @Scope("singleton")
    public DBUtil dbUtilBean() {
        return new DBUtil("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/spring?useUnicode=true&serverTimezone=UTC", "root", "");
    }
    @Bean
    @Scope("prototype")
    public Posts postsBean() {
        return new Posts();
    }
    @Bean
    @Scope("prototype")
    public Comments commentsBean() {
        return new Comments();
    }
    @Bean
    @Scope("prototype")
    public Users usersBean() {
        return new Users();
    }

}
