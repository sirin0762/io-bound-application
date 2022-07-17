package class101.foo.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PostCacheService {

    @Autowired
    PostRepository postRepository;

    private Page<Post> firstPostPage;

    @Scheduled(cron = "* * * * * *")
    public void updateFirstPage() {
        firstPostPage = postRepository.findAll(
            PageRequest.of(0, 20, Sort.by("id").descending())
        );
    }

    public Page<Post> getFirstPage() {
        return this.firstPostPage;
    }

}
