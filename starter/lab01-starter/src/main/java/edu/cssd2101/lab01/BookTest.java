package edu.cssd2101.lab01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
@Test
void normalizesIsbn(){
    Book book=new Book(" 13-456-88910 ","Douce Imane", "Djene Siby", 1835, 2019);
    assertEquals ("1345688910", book.isbn());

}


@Test
void freeBooks(){
    Book book=new Book("1375688910","heritier","Fatima",0, 2021);
    assertEquals(0, book.priceCents());


}

@Test
void yearEndpoint1(){
    Book b=new Book("1234567890","Amour", "Alice", 2400,1450);
    assertEquals(1450, b.year());

}

@Test
void yearEndpoint2(){
    Book b=new Book("1234567890111","Alo", "Kindi", 5600,2027);
    assertEquals(2027, b.year());

}
@Test
void invalidAdjacentYear(){
    assertTrows(IllegalArgumentException.class, ()->new Book("1234567890211","Aloha", "Oumou", 7900,1449));

}

@Test
void invalidAdjacentYear2(){
    assertThrows(IllegalArgumentException.class, ()->new Book("1234567890311","Alim", "Ayman", 1900,2028));

}

@Test
void nullTitle(){
    assertThrows(NullPointerException.class, ()->new Book ("1234567690311",null,"Ayra", 28500, 2025));
}

@Test
void nullAuthor(){
    assertThrows(NullPointerException.class, ()-> new Book ("1234567770311","Love",null, 24500,2021));
}

@Test
void blankText(){
    assertThrows(IllegalArgumentException.class, ()->new Book ("1234567890310","  ","Ali",5404,1500));
}

@Test
void malformedIdentifiers() {
    assertThrows(IllegalArgumentException.class, () -> new Book("1234", "life", "Hery", 3456, 2015));
    assertThows(IllegalArgumentException.class, () -> new Book("ABCDEFGHLM", "Me", "She", 4567, 2014));
    assertThrows(IllegalArgumentException.class, () -> new Book("123456789x", "Ella", "Elsa", 3456, 2012));

}


@Test
void symmetry(){
    Book book1=new Book(" 13-456-88910 ","Douce Imane", "Djene Siby", 1835, 2019);
    Book book2=new Book("1345688910 ","faith", "Ella", 5890, 2012);
    assertTrue(book1.equals(book2));
    assertTrue(book2.equals(book1));

}

@Test
void SameHashCode(){
    assertEquals(book1.hashCode(),book2.hashCode());

}
@Test
void comparison() {
    assertEquals (0,book1.compareTo(book2));

}


@Test
void sameTitle(){
    Book book3=new Book(" 13-456-88910 ","Imane", "Sidibe", 1845, 2015);
    Book book4=new Book("134568891X ","Imane", "Mimo", 5890, 2012);
    assertTrue(Book.DISPLAY_ORDER.compare(book3, book4) < 0);
}


@Test
void treeSet(){
    TreeSet<Book> books=new TreeSet<>();
    assertTrue(books.add(book3);
    assertTrue(books.add)(book4);
    assertEquals(2,books.size());
}


