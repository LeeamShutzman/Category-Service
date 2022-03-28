package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.stringtemplate.language.Cat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@WebMvcTest(CatergoryController.class)
class CatergoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CatergoryController catergoryController;

    @MockBean
    CategoryService categoryService;

    public Category category1 = new Category(100, "chicken", "Fresh chicken");
    public Category category2 = new Category(101, "games", "elden ring");
    public Category category3 = new Category(102, "T-rex", "dinosours");
    public Category category4 = new Category(103, "souls", "darksouls 3");

    public List<Category> testList = new ArrayList<>(Arrays.asList(category1,category2,category3,category4));

    @Test
    void addCategory() throws Exception
    {
        when(categoryService.addCategory(any(Category.class))).thenReturn(category1);
        mockMvc.perform(MockMvcRequestBuilders.post("/categories/add")
            .content(new ObjectMapper().writeValueAsString(category1))
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryID").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value("chicken"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Fresh chicken"));
    }

    @Test
    void getAllCategoried() throws Exception
    {
        when(categoryService.findAll()).thenReturn(testList);
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/all"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]categoryID").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]categoryName").value("chicken"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]description").value("Fresh chicken"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1]categoryID").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]categoryName").value("games"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]description").value("elden ring"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[2]categoryID").value(102))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]categoryName").value("T-rex"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]description").value("dinosours"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[3]categoryID").value(103))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]categoryName").value("souls"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]description").value("darksouls 3"));

    }

    @Test
    void getCategoryByID () throws Exception
    {
        Optional<Category> categoryOptional = Optional.of(category1);
        when(categoryService.findByCategoryID(100L)).thenReturn(categoryOptional);
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/getCategoryByID?categoryID=100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryID").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value("chicken"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Fresh chicken"));
    }

    @Test
    void deleteCategory() throws Exception
    {
        Optional<Category> categoryOptional = Optional.of(category1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/delete?categoryID=100"));
        verify(categoryService,times(1)).deleteCategory(100L);
    }


    @Test
    void updateCategory() throws Exception
    {
        Category newCategory = new Category();
        newCategory.setCategoryName("New Name");
        Category expectedCategory = new Category(100, "New Name", category1.getDescription());
        when(categoryService.updateCategory(eq(100L),any(Category.class))).thenReturn(expectedCategory);
        when(categoryService.updateCategory(eq(105L),any(Category.class))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/categories/update?categoryID=100")
                .content(new ObjectMapper().writeValueAsString(newCategory))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryID").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value("New Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Fresh chicken"));

        mockMvc.perform(MockMvcRequestBuilders.put("/categories/update?categoryID=105")
                .content(new ObjectMapper().writeValueAsString(newCategory))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());

    }


}