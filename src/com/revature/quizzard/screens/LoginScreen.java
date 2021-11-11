package com.revature.quizzard.screens;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;

// TODO: Implement me!
public class LoginScreen extends Screen {

    private final UserService userService;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

        System.out.println("Please provide your credentials to log into your account.");
        System.out.print("Username > ");
        String username = consoleReader.readLine();
        System.out.print("Password > ");
        String password = consoleReader.readLine();

        try {
            AppUser authenticatedUser = userService.authenticateUser(username, password);
            System.out.println("Credentials validated, matching user found: " + authenticatedUser);
            router.navigate("/dashboard");
        } catch (AuthenticationException e) {
            System.out.println("Incorrect credentials provided! No matching user account found.");
        }

    }

}
