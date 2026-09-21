package vn.edu.hcmute.categoryapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.hcmute.categoryapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @EntityGraph(attributePaths = {"user", "category"})
    List<Product> findAll();

    @Override
    @EntityGraph(attributePaths = {"user", "category"})
    Optional<Product> findById(Long id);

    @EntityGraph(attributePaths = {"user", "category"})
    List<Product> findAllByOrderByPriceAsc();

    @EntityGraph(attributePaths = {"user", "category"})
    List<Product> findByCategory_Id(Long categoryId);

    @EntityGraph(attributePaths = {"user", "category"})
    List<Product> findByCategory_IdOrderByPriceAsc(Long categoryId);

    boolean existsByCategory_Id(Long categoryId);
}