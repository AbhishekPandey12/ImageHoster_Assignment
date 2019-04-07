package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  //The method calls the createCommentForImage() method in the Repository and passes the Comment to be inserted in the database
  public void createCommentForImage(Comment toAddCommentToImage){
    commentRepository.createCommentForImage(toAddCommentToImage);
  }
}