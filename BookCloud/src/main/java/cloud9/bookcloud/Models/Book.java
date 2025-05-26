package cloud9.bookcloud.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private int pages;
    private String category;

    private boolean lido = false;
    private boolean queroLer = false;

    public Book() {}

    public Book(String title, String author, String publisher, int pages, String category) {
        this.title     = title;
        this.author    = author;
        this.publisher = publisher;
        this.pages     = pages;
        this.category  = category;
    }

    // Getters e Setters
    public Long getId() { 
        return id; 
    }

    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getAuthor() { 
        return author; 
    }
    public void setAuthor(String author) { 
        this.author = author; 
    }

    public String getPublisher() { 
        return publisher; 
    }
    public void setPublisher(String publisher) { 
        this.publisher = publisher; 
    }

    public int getPages() { 
        return pages; 
    }
    public void setPages(int pages) { 
        this.pages = pages; 
    }

    public boolean isLido() { 
        return lido; 
    }
    public void setLido(boolean lido) { 
        this.lido = lido; 
    }

    public boolean isQueroLer() { 
        return queroLer; 
    }
    public void setQueroLer(boolean queroLer) { 
        this.queroLer = queroLer; 
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Transient
    public String getStatus() {
        if (lido) {
            return "Lido";
        } else if (queroLer) {
            return "Quero Ler";
        }
        return "-";
    }
}