package fmi.project.repository;

import fmi.project.models.ListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookListRepository extends JpaRepository<ListModel, Long> {

    ListModel getByListName(String listName);
}
