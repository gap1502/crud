package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {
  private final List<Post> posts = Collections.synchronizedList(new ArrayList<>());
  private long count = 1;

  public List<Post> all() {
    return posts;
  }

  public Optional<Post> getById(long id) {
    Post value = postByID(id);
    return value != null ? Optional.of(value) : Optional.empty();
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(count);
      count++;
      posts.add(post);
      return post;
    }
    Post value = postByID(post.getId());
    if (value != null) {
      posts.set(posts.indexOf(value), post);
    }
    return value;
  }

  public void removeById(long id) {
    Post value = postByID(id);
    if (value !=null) {
      posts.remove(value);
    }
  }

  private Post postByID(long id) {
    return posts.stream()
            .filter(value -> value.getId() == id)
            .findFirst()
            .orElse(null);
  }
}
