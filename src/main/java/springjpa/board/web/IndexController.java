package springjpa.board.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springjpa.board.config.auth.LoginUser;
import springjpa.board.config.auth.dto.SessionUser;
import springjpa.board.service.PostsService;
import springjpa.board.web.dto.PostsSaveRequestDto;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(@LoginUser SessionUser user, Model model) {
        if(user != null)
            model.addAttribute("user", user);

        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(@LoginUser SessionUser user, Model model) {
        if(user != null)
            model.addAttribute("user", user);

        model.addAttribute("form", new PostsSaveRequestDto());
        return "pages/posts/postWriteForm";
    }

    @GetMapping("/posts/view/{id}")
    public String postView(@LoginUser SessionUser user, @PathVariable("id") Long id, Model model) {
        if(user != null)
            model.addAttribute("user", user);

        model.addAttribute("post", postsService.findById(id));
        return "pages/posts/postView";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@LoginUser SessionUser user, @PathVariable("id") Long id, Model model) {
        if(user != null)
            model.addAttribute("user", user);

        model.addAttribute("post", postsService.findById(id));
        return "pages/posts/postUpdateForm";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "pages/login/login";
//    }
}
