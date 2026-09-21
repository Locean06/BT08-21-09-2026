package vn.edu.hcmute.categoryapp.graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.edu.hcmute.categoryapp.entity.Category;
import vn.edu.hcmute.categoryapp.entity.Product;
import vn.edu.hcmute.categoryapp.entity.User;
import vn.edu.hcmute.categoryapp.service.CategoryService;
import vn.edu.hcmute.categoryapp.service.ProductService;
import vn.edu.hcmute.categoryapp.service.UserService;

@Controller
public class GraphQLController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    public GraphQLController(
            ProductService productService,
            CategoryService categoryService,
            UserService userService) {

        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @QueryMapping
    public List<Product> products() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public List<Product> productsByPriceAsc() {
        return productService.getProductsByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(
            @Argument Long categoryId) {

        return productService.getProductsByCategory(categoryId);
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productService.getProductById(id);
    }

    @QueryMapping
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    @QueryMapping
    public Category category(@Argument Long id) {
        return categoryService.getCategoryById(id);
    }

    @QueryMapping
    public List<User> users() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User user(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping
    public Product createProduct(
            @Argument String title,
            @Argument Integer quantity,
            @Argument String desc,
            @Argument Double price,
            @Argument String images,
            @Argument Long userId,
            @Argument Long categoryId) {

        return productService.createProduct(
                title,
                quantity,
                desc,
                price,
                images,
                userId,
                categoryId
        );
    }

    @MutationMapping
    public Product updateProduct(
            @Argument Long id,
            @Argument String title,
            @Argument Integer quantity,
            @Argument String desc,
            @Argument Double price,
            @Argument String images,
            @Argument Long userId,
            @Argument Long categoryId) {

        return productService.updateProduct(
                id,
                title,
                quantity,
                desc,
                price,
                images,
                userId,
                categoryId
        );
    }

    @MutationMapping
    public boolean deleteProduct(
            @Argument Long id) {

        return productService.deleteProduct(id);
    }

    @MutationMapping
    public Category createCategory(
            @Argument String name,
            @Argument String images) {

        return categoryService.createCategory(
                name,
                images
        );
    }

    @MutationMapping
    public Category updateCategory(
            @Argument Long id,
            @Argument String name,
            @Argument String images) {

        return categoryService.updateCategory(
                id,
                name,
                images
        );
    }

    @MutationMapping
    public boolean deleteCategory(
            @Argument Long id) {

        return categoryService.deleteCategory(id);
    }
}