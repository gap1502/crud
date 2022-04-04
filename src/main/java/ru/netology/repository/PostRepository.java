package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private final ConcurrentMap<Long, Post> posts = new ConcurrentHashMap<>();
  private final AtomicLong idCount = new AtomicLong();

  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long id = idCount.incrementAndGet();
      post.setId(id);
      posts.put(id, post);
    } else if (post.getId() != 0 && !posts.containsKey(post.getId())) {
      throw new NotFoundException();
    } else {
      Long countId = post.getId();
      posts.put(countId, post);
    }
    return post;
  }

  public void removeById(long id) {
    if (posts.containsKey(id)) {
      posts.remove(id);
    } else {
      throw new NotFoundException();
    }
  }
}
