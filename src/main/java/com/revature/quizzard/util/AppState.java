package com.revature.quizzard.util;

import com.revature.quizzard.daos.AppUserDAO;
import com.revature.quizzard.daos.BankDAO;
import com.revature.quizzard.screens.DashboardScreen;
import com.revature.quizzard.screens.LoginScreen;
import com.revature.quizzard.screens.RegisterScreen;
import com.revature.quizzard.screens.WelcomeScreen;
import com.revature.quizzard.screens.bank.*;
import com.revature.quizzard.services.BankService;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    Encapsulate of application state. We will create a few things in here that will be used throughout the
    application:
        - a common BufferedReader that all screens can use to read from the console
        - a ScreenRouter that can be used to navigate from one screen to another
        - a boolean that indicates if the app is still running or not
 */
public class AppState {

    private final Logger logger;
    private static boolean appRunning;
    private final ScreenRouter router;

    public AppState() {

        logger = Logger.getLogger(true);
        logger.log("Initializing application...");

        appRunning = true;
        router = new ScreenRouter();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        AppUserDAO userDAO = new AppUserDAO();
        UserService userService = new UserService(userDAO);

        userService.authenticateUser("asd", "asd"); // test - to avoid login

        BankDAO bankDAO  = new BankDAO();
        BankService bankService = new BankService(bankDAO, userService);

        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new RegisterScreen(consoleReader, router, userService));
        router.addScreen(new LoginScreen(consoleReader, router, userService));
        router.addScreen(new DashboardScreen(consoleReader, router, userService));

        router.addScreen(new OpenAccountScreen(consoleReader, router, bankService));
        router.addScreen(new DepositScreen(consoleReader, router, bankService));
        router.addScreen(new WithdrawScreen(consoleReader, router, bankService));
        router.addScreen(new ViewBalancesScreen(consoleReader, router, bankService));
        router.addScreen(new ViewTransactionScreen(consoleReader, router, bankService));


        logger.log("Application initialized!");

    }

    public void startup() {

        try {
            while (appRunning) {
                //router.navigate("/welcome");
                router.navigate("/dashboard"); // test
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        appRunning = false;
    }
}
