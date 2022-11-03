package miniproject.board.controller;

import miniproject.board.domain.Boards;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @GetMapping("/boardlist")
    public String  boradlist(){
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
        System.out.println(boards.getTitle() + ":" + boards.getContent());
        return "redirect:/boardlist";
    }
}
