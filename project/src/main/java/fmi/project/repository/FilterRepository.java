package fmi.project.repository;

import fmi.project.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<BookModel, Long> {

    List<BookModel> findByAuthor(String author);
    List<BookModel> findByGenre(String genre);
}
