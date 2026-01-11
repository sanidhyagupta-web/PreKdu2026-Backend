//package com.example.demo.Service;
//
//import com.example.demo.Exception.ResourceNotFoundException;
//import com.example.demo.Models.Book;
//import com.example.demo.Repository.BookRepository;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doNothing;
//
//// We need to enable this Test class with Mockito Extensions
//@ExtendWith(MockitoExtension.class)
//class BookServiceTestLearning {
//
//    @BeforeAll // init method will run before running any test . It will run only once.
//    public static void init(){
//        System.out.println("BeforeAll"); // Suppose you are mocking a DB then the jdbc connection can be
//                                         // established over here.
//    }
//
//    @BeforeEach // Here the method runs before each test .
//    public void initBeforeEach(){
//        System.out.println("BeforeEach"); // Used to create a test environment , if required.
//    }
//
//    // Creating a real object in Test is not a Feasible approach . So , we need to create a mock object for
//    // it and for that we use Mockito.
//    @InjectMocks
//    private BookService bookService;
//
//    @Mock
//    private BookRepository bookRepository; // We need to inject this in bookService , it acts as a dependency
//
//    @Test
//    void AddBookShouldAddBookSuccessfully(){
//
//        // Arranging the test data
//        Book book = new Book();
//        book.setBookId(3);
//        book.setAuthor("Jerome K Jerome");
//        book.setTitle("Three Mens in A Boat");
//
//        // Mocking the behavior inside the function
//        Mockito.when(bookRepository.save(book)).thenReturn(book);
//
//        // Calling the method
//        Book AddedBook = bookService.AddBook(book);
//
//        // test book = matched book
//        // Assertions
//        assertEquals(book.getBookId(),AddedBook.getBookId());
//        assertNotNull(AddedBook);
//    }
//
//    @Test
//    void deleteProductShouldDeleteProductSuccessfully(){
//        doNothing().when(bookRepository).deleteById(52); // for testing void methods , however not useful here
//        bookService.DeleteBook(52);
//        // we can assert here that delete method should be called at least once , if Id exist
//        //Mockito.verify(bookRepository,Mockito.times(1)).deleteById(52);
//        assertThrows(ResourceNotFoundException.class,()->bookService.DeleteBook(52));
//    }
//
//    @AfterEach // Executed after each test method is executed
//    public void cleanUp(){
//        System.out.println("AfterEach"); // Can be used to reset the value of few variables after each test
//    }
//
//    @AfterAll // Executed after all the test methods are executed
//    public static void destroy(){
//        System.out.println("AfterAll"); // Destroy the db connections
//    }
//
//}
//// Test LifeCycle : BeforeAll -> BeforeEach -> Test -> AfterEach -> AfterAll
//
//// Private methods will come in the flow of public methods and one must write the test cases for the public methods