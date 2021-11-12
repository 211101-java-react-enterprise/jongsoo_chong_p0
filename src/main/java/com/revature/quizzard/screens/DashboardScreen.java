package com.revature.quizzard.screens;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.ScreenRouter;

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

            String menu =
                    "1) Open a Bank account\n" +
                    "2) Deposit funds into an account\n" +
                    "3) Withdraw funds from an account\n" +
                    "4) View the balance of my account(s)\n" +
                    "5) Logout\n" +
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
                    System.out.println("View/edit flashcards selected");
                    break;
                case "4":
                    System.out.println("View/edit flashcards selected");
                    break;
                case "5":
                    userService.logout();
                    break;
                default:
                    System.out.println("The user made an invalid selection");
            }
        }

    }

}
