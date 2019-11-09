package com.example.todoapp.models;

import java.util.Date;
import java.util.List;
// import javax.persistence.CascadeType;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="todos")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Todo {
    @Id
    private String id;

    @NotBlank
    @Size(max=500)
    @Indexed(unique=false)
    private String description;

    enum TodoStatus { NEW, IN_PROGRESS, DONE, CANCELLED }
    @Indexed(unique=false)
    private TodoStatus status = TodoStatus.NEW;

    @Indexed(unique=false)
    private Date createdAt = new Date();

    /*
     * Using OneToMany with JoinColumn instead of a Foreign Key because File model could be realted to other classes.
     */
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "id")
    private List<File> files;

    public Todo() {
        super();
    }

    public Todo(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return String.format(
            "Todo[id=%s, description='%s', status='%s']",
            id, description, status
        );
    }
}
