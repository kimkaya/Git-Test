package miniproject.board.service;

import miniproject.board.domain.Boards;
import miniproject.board.repository.JpaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private final JpaBoardRepository repository;

    public BoardService(JpaBoardRepository repository) {
        this.repository = repository;
    }

    public void BoardSave(Boards boards){
       repository.save(boards);
    }

    public Page<Boards> boardsList(Pageable pageable){
        Page<Boards> boards = repository.findAll(pageable);
        return boards;
    }

    public  void boardDelete(Integer id){
        repository.deleteById(id);
    }

    public Boards boards(Integer id){
        Optional<Boards> boardsList = repository.findById(id);
        System.out.println(boardsList.get());
        return boardsList.get();
    }

}
