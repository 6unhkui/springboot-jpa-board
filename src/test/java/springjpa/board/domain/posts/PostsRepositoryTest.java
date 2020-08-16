package springjpa.board.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void 게시글_저장_불러오기() throws Exception {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Posts savePost = postsRepository.save(Posts.builder().title(title).content(content).author("inkyung").build());

        //when
        Posts posts = postsRepository.findById(savePost.getId()).orElse(null);

        //then
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }


    @Test
    public void BaseTimeEntity_테스트() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.now();
        Posts savedPosts = postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        Posts posts = postsRepository.findById(savedPosts.getId()).orElseThrow(() ->
                new IllegalArgumentException("찾을 수 없습니다.")
        );

        //then
        System.out.println("createDate = " + posts.getCreatedDate());
        System.out.println("modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}