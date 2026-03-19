package com.example.liquibase;

import com.example.liquibase.controllers.UsersController;
import com.example.liquibase.model.Users;
import com.example.liquibase.repository.UserRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    UserRepo userRepo;
    @InjectMocks
    UsersController usersController;

    @Test
    public void testMethod() {
        System.out.println("hello Test");
    }

    @BeforeAll
    public static void init(){
       System.out.println("Before all test method called");
    }

    @BeforeEach
    public void initBeforTest(){
        System.out.println("before each test called");
    }

    @Test
    public void saveUserTest(){
        System.out.println("Testing user");
        Users user=new Users();
        when(usersController.saveUser(user)).thenReturn(user);
        Users addedUser= usersController.saveUser(user);
        assertEquals(addedUser.getId(),user.getId());
        assertNotNull(addedUser);

    }
    @Test
    public void  testuserName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateName = UsersController.class.getDeclaredMethod("validateName", String.class);
        System.out.println("Printing method : "+validateName);
        boolean b = validateName.trySetAccessible();
        System.out.println("rpinting the accessible value : "+b);
        Boolean mohit =(Boolean) validateName.invoke(usersController, "MohitPatel");
        assertTrue(mohit);
        System.out.println("test cases passed successfully" );
    }
}
