package com.microservices.users_services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import com.microservices.users_services.resolvers.UserResolver;

@Configuration
public class GraphQLConfig {
    
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer(UserResolver userResolver) {
        return wiringBuilder -> wiringBuilder
            .type("Query", typeWiring -> typeWiring
                .dataFetcher("users", userResolver.getUsers())
                .dataFetcher("user", userResolver.getUserById()))
            .type("Mutation", typeWiring -> typeWiring
                .dataFetcher("createUser", userResolver.createUser())
                .dataFetcher("updateUser", userResolver.updateUser())
                .dataFetcher("deleteUser", userResolver.deleteUser()));
    }

    /* @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return withDefaults -> {

        };
    } */
}
