package com.revature.bankjsc.screens;

import com.revature.bankjsc.models.AppUser;
import com.revature.bankjsc.services.UserService;
import com.revature.bankjsc.util.ScreenRouter;

import java.io.BufferedReader;

public class DashboardScreen extends Screen {

    private final UserService userService;

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

        // TODO Implement user options and make this screen loop until the user logs out.
        AppUser sessionUser = userService.getSessionUser();
        if (sessionUser == null) {
            System.out.println("You are not currently logged in! Navigating to Login Screen");
            router.navigate("/login");
            return;
        }

        while (userService.isSessionActive()) {
            System.out.printf("\n%s's Dashboard\n", sessionUser.getFirstName());

            String menu = "1) View/edit my profile information\n" +
                    "2) View/create study sets\n" +
                    "3) View/create flashcards\n" +
                    "4) Logout\n" +
                    "> ";

            System.out.print(menu);

            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("View/edit profile selected");
                    break;
                case "2":
                    System.out.println("View/edit study sets selected");
                    break;
                case "3":
                    router.navigate("/flashcards");
                    break;
                case "4":
                    userService.logout();
                    break;
                default:
                    System.out.println("You have made an invalid selection");
            }
        }

    }

}
