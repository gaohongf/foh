package xyz.fmcy.foh.controller.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.fmcy.foh.pojo.*;
import xyz.fmcy.foh.service.CommentsService;
import xyz.fmcy.foh.service.TopicService;
import xyz.fmcy.foh.service.UserService;
import xyz.fmcy.foh.vo.UserTopicPage;
import xyz.fmcy.foh.vo.VComments;
import xyz.fmcy.foh.vo.VUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 付高宏
 * @date 2022/6/17 18:10
 */
@Controller
public class TopicController {
    @Resource
    private TopicService topicService;
    @Resource
    private UserService userService;

    @Resource
    private CommentsService commentsService;
    @Resource
    private Integer commentsPageNumber;

    @GetMapping("/topic/read/{id}")
    public String topicPage(@PathVariable Integer id, Model model) {
        Topic topic = topicService.getTopicById(id);
        User author = userService.findUserByUid(topic.getAuthorid());
        Avatar avatar = userService.findAvatarByUid(author.getId());
        model.addAttribute("topicContext", topic);
        model.addAttribute("topicAuthor", author);
        model.addAttribute("authorAvatar", avatar);
        model.addAttribute("comments", commentsService.findByTopicId(id, 0).stream()
                .map((comments -> new VComments(new VUser(userService.findUserByUid(comments.getUid())), userService.findAvatarByUid(comments.getUid()), comments)))
                .peek(vComments -> vComments.setSubComments(commentsService.subComments(vComments.getId()).stream()
                        .map((comments -> new VComments(new VUser(userService.findUserByUid(comments.getUid())), userService.findAvatarByUid(comments.getUid()), comments))).collect(Collectors.toList()))
                ).collect(Collectors.toList()));
        model.addAttribute("louStart", 1);
        model.addAttribute("commentLooker", (Function<Integer, VComments>) targetId -> {
            Comments comment = commentsService.getCommentById(targetId);
            return new VComments(new VUser(userService.findUserByUid(comment.getUid())), userService.findAvatarByUid(comment.getUid()), comment);
        });
        return "/topic";
    }

    @GetMapping("/topic/comments/{tid}/{page}")
    public String topicComments(@PathVariable Integer tid, @PathVariable Integer page, Model model) {
        model.addAttribute("comments", commentsService.findByTopicId(tid, page).stream().map(
                (comments -> new VComments(new VUser(userService.findUserByUid(comments.getUid())), userService.findAvatarByUid(comments.getUid()), comments))
        ).collect(Collectors.toList()));
        model.addAttribute("louStart", commentsPageNumber * page + 1);
        return "/topic::topicComments";
    }

    /**
     * 查看用户的帖子
     */
    @GetMapping("/topic/user/{userid}/{page}")
    public String loadUserTopics(@PathVariable Integer page, @PathVariable Integer userid, Model model) {
        List<Topic> topicByUserId = topicService.findTopicByUserId(userid, page).stream().peek(
                //仅展示部分内容
                topic -> topic.setContent(topic.getContent().replaceAll("^(\\S{0,32})(\\S*)$", "$1..."))
        ).collect(Collectors.toList());
        Boolean hasNext = topicService.findTopicByUserId(userid, page + 1).size() > 0;
        model.addAttribute("userid", userid);
        model.addAttribute("page", page + 1);
        model.addAttribute("userTopicPage", new UserTopicPage(hasNext, topicByUserId));
        model.addAttribute("types", topicService.getTopicTypes().stream().collect(Collectors.toMap(TopicType::getId, TopicType::getTypename)));
        return "/user-frames::userTopic";
    }

    @GetMapping("/topic/type/{id}/{number}")
    public String topicReferralPage(@PathVariable Integer id, @PathVariable Integer number, Model model, HttpSession session) {
        model.addAttribute("type", topicService.findTopicTypeById(id));
        model.addAttribute("userAvatar", userService.findAvatarByUid(((User) session.getAttribute("user")).getId()));
        model.addAttribute("userService", userService);
        model.addAttribute("topics", topicService.randTopicByType(id, number));
        return "/minindex";
    }

    @PostMapping("/topic/reply/{tid}")
    public String addComments(String content, @PathVariable Integer tid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Comments comments = new Comments();
        comments.setTargetId(tid);
        comments.setContent(content);
        comments.setUid(user.getId());
        comments.setTargetType(0);
        comments.setParent(tid);
        commentsService.addComment(comments);
        return "redirect:/topic/read/" + tid;
    }

    @PostMapping("/topic/reply/comment/{tid}")
    public String addSubComment(@PathVariable Integer tid, String content, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Comments comments = new Comments();
        comments.setUid(user.getId());
        comments.setContent(content);
        comments.setTargetId(tid);
        comments.setTargetType(1);
        Comments commentById = commentsService.getCommentById(tid);
        if (Integer.valueOf(0).equals(commentById.getTargetType())) {
            comments.setParent(commentById.getId());
        } else {
            comments.setParent(commentById.getParent());
        }
        if (commentsService.addComment(comments)) {
            Comments newComment = commentsService.getCommentById(comments.getId());
            VComments comments1 = new VComments(new VUser(user), userService.findAvatarByUid(user.getId()), newComment);
            VComments comments2 = new VComments();
            comments2.setSubComments(List.of(comments1));
            model.addAttribute("comment", comments2);
            model.addAttribute("commentLooker", (Function<Integer, VComments>) targetId -> {
                Comments comment = commentsService.getCommentById(targetId);
                return new VComments(new VUser(userService.findUserByUid(comment.getUid())), userService.findAvatarByUid(comment.getUid()), comment);
            });
        }
        return "/topic::subComments";
    }
}
