package springjpa.board.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springjpa.board.service.PostsService;
import springjpa.board.web.dto.PostsSaveRequestDto;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "home";
    }

    @GetMapping("/posts/save")
    public String postSave(Model model) {
        model.addAttribute("form", new PostsSaveRequestDto());
        return "pages/posts/postWriteForm";
    }

    @GetMapping("/posts/view/{id}")
    public String postView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "pages/posts/postView";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "pages/posts/postUpdateForm";
    }
}
