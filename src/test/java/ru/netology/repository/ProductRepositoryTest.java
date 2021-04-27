package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository;


    @BeforeEach
    public void PrepareData() {
        repository = new ProductRepository();
    }

    @Test
    void shouldRemoveByExistingId() {
        Book first = new Book(1, "The Lord of the Rings: The Fellowship of the Ring", 200, "Tolkien", 500, 2001);
        Book second = new Book(2, "The Lord of the Rings: The Two Towers", 200, "Tolkien", 600, 2002);

        repository.save(first);
        repository.save(second);

        repository.removeById(first.getId());

        Product[] expected = {second};

        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdNotExist() {
        assertThrows(NotFoundException.class, () -> repository.removeById(4));
    }
}
