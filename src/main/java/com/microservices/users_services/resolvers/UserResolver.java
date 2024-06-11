package com.microservices.users_services.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.microservices.users_services.models.User;
import com.microservices.users_services.services.UserService;

import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetcher;

@Component
public class UserResolver implements GraphQLQueryResolver /* , GraphQLMutationResolver */ {
    // @Autowired
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
            Integer userId = Integer.parseInt(id);
            return userService.getUserById(userId);
        };
    }

    @SuppressWarnings("deprecation")
    public DataFetcher<User> createUser() {
        return dataFetchingEnvironment -> {
            String username = dataFetchingEnvironment.getArgument("username");
            String email = dataFetchingEnvironment.getArgument("email");
            String password = dataFetchingEnvironment.getArgument("password");
            String role = dataFetchingEnvironment.getArgument("role");

            // Validaciones básicas
            if (StringUtils.isEmpty(username)) {
                throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
            }
            if (StringUtils.isEmpty(email)) {
                throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
            }
            if (StringUtils.isEmpty(password)) {
                throw new IllegalArgumentException("La contraseña no puede estar vacía");
            }
            if (StringUtils.isEmpty(role)) {
                throw new IllegalArgumentException("El rol no puede estar vacío");
            }

            User existingUserByEmail = userService.getUserByEmail(email);
            System.out.println("existing email: "+existingUserByEmail);
            if (existingUserByEmail != null) {
                throw new GraphQLException("El correo electrónico ya está registrado");
            }

            User existingUserByUsername = userService.getUserByUsername(username);
            System.out.println("existing username: "+existingUserByUsername);
            if (existingUserByUsername != null) {
                throw new GraphQLException("El nombre de usuario ya está en uso");
            }

            // Crear el usuario si pasa todas las validaciones
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            return userService.createUser(user);
        };
    }

    public DataFetcher<User> updateUser() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            String username = dataFetchingEnvironment.getArgument("username");
            String email = dataFetchingEnvironment.getArgument("email");
            String password = dataFetchingEnvironment.getArgument("password");
            String role = dataFetchingEnvironment.getArgument("role");
            User user = new User();

            if (username != null)
                user.setUsername(username);
            if (email != null)
                user.setEmail(email);
            if (password != null)
                user.setPassword(password);
            if (role != null)
                user.setRole(role);

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
