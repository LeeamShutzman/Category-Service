package com.example.demo.services;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CategoryService.class)
class CategoryServiceTest {


    @Autowired
    CategoryService categoryService;

    @MockBean
    CategoryRepository categoryRepository;

    //given for all
    public final Category category1 = new Category(100, "chicken", "Fresh chicken");
    public final Category category2 = new Category(101, "games", "elden ring");
    public final Category category3 = new Category(102, "T-rex", "dinosours");
    public final Category category4 = new Category(103, "souls", "dark souls 3");

    public List<Category> testList = new ArrayList<>(Arrays.asList(category1,category2,category3,category4));


    @Test
    void should_TestTrue_WhenAdding_Catigory()
    {

        //when
        when(categoryRepository.save(category1)).thenReturn(category1);

        //then
        assertEquals(category1.toString(),categoryService.addCategory(category1).toString());
    }

    @Test
    void should_ReturnTrue_WhenFindsList()
    {
        //when
        when(categoryRepository.findAll()).thenReturn(testList);

        //then
        assertEquals(testList,categoryService.findAll());
    }

    @Test
    void should_ReturnTrueCategoryID_When_ID_IsLookedUp()
    {
        Optional<Category> categoryOptional = Optional.of(category1);

        //when
        when(categoryRepository.findById(100L)).thenReturn(categoryOptional);

        //then
        assertEquals(categoryOptional,categoryService.findByCategoryID(100L));
    }

    @Test
    void should_ReturnNullCategoryId_when_OptionalListIsEmpty()
    {
        Optional<Category> categoryOptional = Optional.empty();

        //when
        when(categoryRepository.findById(50L)).thenReturn(categoryOptional);

        //then
        assertEquals(categoryRepository.findById(50L),categoryOptional);
    }

    @Test
    void should_ReturnTrue_WhenCategoryIs_Deleted()
    {
        Optional<Category> categoryOptional = Optional.of(category1);

        //when
        categoryRepository.deleteById(100L);
        //then
        verify(categoryRepository, times(1)).deleteById(100L);
    }

    @Test
    void should_ReturnTrueWhen_CategoryIsUpdated()
    {
        //given
        Category expectedCategory = new Category(category1.getCategoryID(), "new category name", category1.getDescription());
        Category newCategory = new Category();
        newCategory.setCategoryName("new category name");
        Optional<Category> categoryOptional = Optional.of(new Category(category1.getCategoryID(), category1.getCategoryName(), category1.getDescription()));

        //when
        when(categoryRepository.findById(100L)).thenReturn(categoryOptional);
        when(categoryRepository.save(any(Category.class))).thenReturn(expectedCategory);
        when(categoryRepository.findById(101L)).thenReturn(Optional.empty());

        //then
        assertEquals(expectedCategory.toString(), categoryService.updateCategory(100L,newCategory).toString());
        assertEquals(null, categoryService.updateCategory(101L, newCategory));



    }

}