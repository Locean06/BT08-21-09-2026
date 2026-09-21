package vn.edu.hcmute.categoryapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.edu.hcmute.categoryapp.entity.Category;
import vn.edu.hcmute.categoryapp.repository.CategoryRepository;
import vn.edu.hcmute.categoryapp.repository.ProductRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy Category có id = " + id
                        ));
    }

    public Category createCategory(
            String name,
            String images) {

        if (categoryRepository.existsByName(name)) {
            throw new RuntimeException(
                    "Category đã tồn tại: " + name
            );
        }

        Category category = new Category();

        category.setName(name);
        category.setImages(images);

        return categoryRepository.save(category);
    }

    public Category updateCategory(
            Long id,
            String name,
            String images) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy Category có id = " + id
                        ));

        category.setName(name);
        category.setImages(images);

        return categoryRepository.save(category);
    }

    public boolean deleteCategory(Long id) {

        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException(
                    "Không tìm thấy Category có id = " + id
            );
        }

        if (productRepository.existsByCategory_Id(id)) {
            throw new RuntimeException(
                    "Không thể xóa Category vì đang có Product thuộc Category này"
            );
        }

        categoryRepository.deleteById(id);

        return true;
    }
}