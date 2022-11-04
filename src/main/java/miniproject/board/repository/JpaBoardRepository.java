package miniproject.board.repository;

import miniproject.board.domain.Boards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaBoardRepository extends JpaRepository<Boards , Integer> {
    default Optional<Boards>findboardByuserId(Integer integer){
        return  Optional.empty();
    }


}
