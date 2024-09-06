package net.enjoy.springboot.registrationlogin.controller;

import jakarta.validation.Valid;
import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.Category;
import net.enjoy.springboot.registrationlogin.entity.Transaction;
import net.enjoy.springboot.registrationlogin.entity.TransactionType;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.service.CategoryService;
import net.enjoy.springboot.registrationlogin.service.TransactionService;
import net.enjoy.springboot.registrationlogin.service.UserService;
import net.enjoy.springboot.registrationlogin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AuthController {
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Fetching current user from SecurityContext.");
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            System.out.println("Current user: " + ((User) authentication.getPrincipal()).getEmail());
            return (User) authentication.getPrincipal();
        }
        System.out.println("No authenticated user found.");
        return null; // Return null if no user is authenticated
    }
    // handler method to handle home page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("transactions", transactions);
        return "dashboardPage";
    }
    @GetMapping("/dash")
    public String dash(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("transactions", transactions);
        return "dashboardPage";
    }
//    @GetMapping("/transactionhistory")
//    public String showTransactionHistory(Model model) {
//        List<Transaction> transactions = transactionService.getAllTransactions();
//        System.out.println("Transaction history page requested.");
//        User currentUser = getCurrentUser();
//        if (currentUser == null) {
//            System.out.println("No authenticated user found. Redirecting to login page.");
//            return "redirect:/finance/login";
//        }
//
//        model.addAttribute("transactions", transactionService.getTransactionsByUser(currentUser)); // Fetch user transactions
//        System.out.println("Transaction history prepared for user: " + currentUser.getEmail());
//        return "testing";
//    }
    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    // GET mapping to display the Add Transaction form
    @GetMapping("/addtransaction")
    public String showAddTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction()); // Add an empty Transaction object

        List<Category> categories = categoryService.getAllCategories(); // Retrieve categories
        model.addAttribute("categories", categories);

        List<TransactionType> transactionTypes = Arrays.asList(TransactionType.values()); // Retrieve transaction types
        model.addAttribute("transactionTypes", transactionTypes);

        return "AddTransaction"; // Ensure this matches your Thymeleaf template filename
    }
    @GetMapping("/transactionhistory")
    public String history(Model model) {
        model.addAttribute("transaction", new Transaction()); // Add an empty Transaction object

        List<Category> categories = categoryService.getAllCategories(); // Retrieve categories
        model.addAttribute("categories", categories);

        List<TransactionType> transactionTypes = Arrays.asList(TransactionType.values()); // Retrieve transaction types
        model.addAttribute("transactionTypes", transactionTypes);

        return "TransactionHistory"; // Ensure this matches your Thymeleaf template filename
    }
//    @GetMapping("/categories")
//    public String categories(Model model) {
//        model.addAttribute("transaction", new Transaction()); // Add an empty Transaction object
//
//        List<Category> categories = categoryService.getAllCategories(); // Retrieve categories
//        model.addAttribute("categories", categories);
//
//        List<TransactionType> transactionTypes = Arrays.asList(TransactionType.values()); // Retrieve transaction types
//        model.addAttribute("transactionTypes", transactionTypes);
//
//        return "CategoriesPage"; // Ensure this matches your Thymeleaf template filename
//    }


    // POST mapping to handle form submission
    @PostMapping("/addtransaction")
    public String processAddTransactionForm(
            @ModelAttribute("transaction") Transaction transaction,
            BindingResult result,
            Model model) {

        // Perform validation checks (if any)
        if (result.hasErrors()) {
            // If validation errors exist, redisplay the form with error messages
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);

            List<TransactionType> transactionTypes = Arrays.asList(TransactionType.values());
            model.addAttribute("transactionTypes", transactionTypes);

            return "addTransaction"; // Return to the form view to display errors
        }

        // Save the transaction to the database
        transactionService.saveTransaction(transaction);

        // Redirect to a success page or back to the form with a success message
        model.addAttribute("message", "Transaction added successfully!");
        return "redirect:/addtransaction"; // Redirect after successful form submission
    }
}