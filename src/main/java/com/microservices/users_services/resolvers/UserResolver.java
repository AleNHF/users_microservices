package com.microservices.users_services.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.microservices.users_services.models.User;
import com.microservices.users_services.services.UserService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetcher;

@Component
public class UserResolver implements GraphQLQueryResolver /*, GraphQLMutationResolver */ {
    //@Autowired
    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    public DataFetcher<List<User>> getUsers() {
        return dataFetchingEnvironment -> userService.getUsers();
    }

    public DataFetcher<Optional<User>> getUserById() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return userService.getUserById(id);
        };
    }

    public DataFetcher<User> createUser() {
        return dataFetchingEnvironment -> {
            String username = dataFetchingEnvironment.getArgument("username");
            String email = dataFetchingEnvironment.getArgument("email");
            String password = dataFetchingEnvironment.getArgument("password");
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            return userService.createUser(user);
        };
    }

    public DataFetcher<User> updateUser() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            String username = dataFetchingEnvironment.getArgument("username");
            String email = dataFetchingEnvironment.getArgument("email");
            String password = dataFetchingEnvironment.getArgument("password");
            User user = new User();

            if (username != null)
                user.setUsername(username);
            if (email != null)
                user.setEmail(email);
            if (password != null)
                user.setPassword(password);

            return userService.updateUser(id, user);
        };
    }

    public DataFetcher<Boolean> deleteUser() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return userService.deleteUser(id);
        };
    }

    public DataFetcher<String> loginUser() {
        return dataFetchingEnvironment -> {
            String username = dataFetchingEnvironment.getArgument("username");
            String password = dataFetchingEnvironment.getArgument("password");
            return userService.loginUser(username, password);
        };
    }
}
