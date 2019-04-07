package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private ImageService imageService;

  //This method is called when the Comments to be added to an Image by the Logged-in User
  //First receive the dynamic parameter in the incoming request URL in a string variable 'imageId', imageTitle and Request Parameter Comment Object.
  //Add the image in the Model type object with 'image' as the key
  //Return 'images/image.html' file
  @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
  public String addCommentsToImage(@PathVariable("imageId") Integer image_id, @PathVariable("imageTitle") String image_title, @RequestParam("comment") String image_comment, HttpSession session){

    Comment comment = new Comment();

    Image image = imageService.getImage(image_id);
    comment.setImage(image);

    User user = (User) session.getAttribute("loggeduser");
    comment.setUser(user);

    comment.setCreatedDate(LocalDate.now());
    comment.setText(image_comment);

    commentService.createCommentForImage(comment);

    return "redirect:/images/" + image.getId() + "/" + image.getTitle();
  }
}
