package com.ecommerce.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.model.Product;
import com.ecommerce.model.Seller;
import com.ecommerce.model.UserDTO;
import com.ecommerce.services.ProductService;
import com.ecommerce.services.UserService;
@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService; // Using `UserService` instead of `sellerService`

	

    @GetMapping("/")
    public String getSellerIndex() {
        return "/seller/index";
    }

    
    @GetMapping({"/manage-products", "/manage-products/"})
    public String getSellerDashboard(Principal principal, Model model) {
        try {
            // Ensure the user is authenticated
            if (principal == null || principal.getName() == null) {
                return "redirect:/login?error=not_authenticated";
            }

            // Get the seller's email and fetch the seller details
            String email = principal.getName();
            UserDTO seller = userService.findByEmail(email);

            // Ensure the logged-in user is a seller
            if (seller == null || !seller.getRole().equalsIgnoreCase("SELLER")) {
                return "redirect:/access-denied"; // Redirect to an access-denied page
            }

            // Fetch products for the logged-in seller
            List<Product> allProducts = productService.getProductsBySeller(seller.getId());

            // Ensure 'enabled' field is not null
            for (Product product : allProducts) {
                if (product.getEnabled() == null) {
                    product.setEnabled(false); // Default value
                }
            }

            // Add the product list to the model
            model.addAttribute("productList", allProducts);

            // Render the seller dashboard
            return "seller/index";
            
        } catch (Exception e) {
            // Log the error and redirect to an error page
           
            return "redirect:/error?message=An unexpected error occurred.";
        }
    }


    @PostMapping("/manage-products/add-product")
    public String postAddProductBySeller(
            @ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile file,
            Principal principal, Model model) {
        
        // Check if the seller is valid
        String email = principal.getName();
        UserDTO seller = userService.findByEmail(email);

        if (seller == null || !seller.getRole().equalsIgnoreCase("SELLER")) {
            model.addAttribute("errorMessage", "Seller not found or unauthorized!");
            return "manage-products"; // Return to the form with an error message
        }

        // Set the seller for the product
        product.setSeller(seller);

        // Check if the image file is missing
        if (file.isEmpty()) {
            model.addAttribute("errorMessage", "Product image is required!");
            return "manage-products"; // Return to the form with an error message
        }

        // Try to save the image file
        try {
            // Ensure the directory exists
            Path path = Path.of("src/main/resources/static/uploads");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Save the image file
            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            // Set image URL in product (adjust this part based on your product entity)
            product.setImage("/uploads/" + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Failed to upload image. Please try again.");
            return "manage-products"; // Return to the form with an error message
        }

        // Add the product to the database
        productService.addProduct(product);

        // Redirect to the product management page
        return "redirect:/seller/manage-products"; // Redirect to the product management page
    }

}

