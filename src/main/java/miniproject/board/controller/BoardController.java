package miniproject.board.controller;

import miniproject.board.domain.Boards;
import miniproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService service;

    public  BoardController(BoardService service){
        this.service = service;
    }

    @GetMapping("/boardlist")
    public String  boradlist(Model model){
        List<Boards> boards = service.boardsList();
        model.addAttribute("boards",boards);
        return "boardlist";
    }

    @GetMapping("board")
    public String  borad(){
        return "board";
    }

    @GetMapping("boardwrite")
    public String  boradwrite(){
        return "boardwrite";
    }

    @PostMapping("/boardwrite")
    public String boardwritepost(Boards boards){
        service.BoardSave(boards);
        return "redirect:/boardlist";
    }
}
