package vn.edu.hcmute.categoryapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.edu.hcmute.categoryapp.entity.Category;
import vn.edu.hcmute.categoryapp.entity.Product;
import vn.edu.hcmute.categoryapp.entity.User;
import vn.edu.hcmute.categoryapp.repository.CategoryRepository;
import vn.edu.hcmute.categoryapp.repository.ProductRepository;
import vn.edu.hcmute.categoryapp.repository.UserRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy Product có id = " + id
                        ));
    }

    public Product createProduct(
            String title,
            Integer quantity,
            String desc,
            Double price,
            String images,
            Long userId,
            Long categoryId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy User có id = " + userId
                        ));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy Category có id = " + categoryId
                        ));

        Product product = new Product();

        product.setTitle(title);
        product.setQuantity(quantity);
        product.setDesc(desc);
        product.setPrice(price);
        product.setImages(images);
        product.setUser(user);
        product.setCategory(category);

        return productRepository.save(product);
    }

    public Product updateProduct(
            Long id,
            String title,
            Integer quantity,
            String desc,
            Double price,
            String images,
            Long userId,
            Long categoryId) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy Product có id = " + id
                        ));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy User có id = " + userId
                        ));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy Category có id = " + categoryId
                        ));

        product.setTitle(title);
        product.setQuantity(quantity);
        product.setDesc(desc);
        product.setPrice(price);
        product.setImages(images);
        product.setUser(user);
        product.setCategory(category);

        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);

        return true;
    }
}