package miniproject.board.controller;

import miniproject.board.domain.Boards;
import miniproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/boardlist")
    public String boardlist
            (Model model ,
             @PageableDefault
                     (page = 0, size = 20, sort = "id" , direction = Sort.Direction.DESC)
             Pageable pageable) {
        Page<Boards> boards = service.boardsList(pageable);
        int nowPage = boards.getPageable().getPageNumber() + 1;
        int startPage = 1;
        int endPage = boards.getTotalPages();

        model.addAttribute("boards", boards);

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";

    }




    @GetMapping("/board/{id}")
    public String board(Model model, @PathVariable("id") Integer id) {
        System.out.println(id);
        Boards board = service.boards(id);
        model.addAttribute("board", board);
        return "board";
    }

    @GetMapping("boardwrite")
    public String boardwrite() {
        return "boardwrite";
    }

    @PostMapping("/boardwrite")
    public String boardwritepost(BoardForm form) {
        Boards board = new Boards();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        service.BoardSave(board);
        return "redirect:/boardlist";
    }

    @PostMapping("/boardUpdate")
    public String boardUpdate(BoardForm form) {
        Boards board = service.boards(form.getId());
        if(form.getTitle() != board.getTitle()){
            board.setTitle(form.getTitle());
        }
        if(form.getContent() != board.getContent()){
            board.setContent(form.getContent());
        }
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        service.BoardSave(board);
        return "redirect:/boardlist";
    }

    @PostMapping("/boardDelete/{id}")
    public String boardDelete(Integer id) {
        service.boardDelete(id);
        return "redirect:/boardlist";
    }

//        <input type="submit" value="수정" th:action="@{/boardUpdate/{id}(id=${board.id})}">
//        <input type="submit" value="삭제" th:action="@{/boardDelete}/{id}(id=${board.id})}">
}
