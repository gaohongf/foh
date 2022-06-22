package xyz.fmcy.foh.controller.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.fmcy.foh.pojo.*;
import xyz.fmcy.foh.service.CommentsService;
import xyz.fmcy.foh.service.FavoritesService;
import xyz.fmcy.foh.service.TopicService;
import xyz.fmcy.foh.service.UserService;
import xyz.fmcy.foh.vo.*;
import xyz.fmcy.foh.vo.combo.KeyAndValue;

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
    @Resource
    private FavoritesService favoritesService;

    @GetMapping("/topic/read/{id}")
    public String topicPage(@PathVariable Integer id, @Nullable Integer page, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Topic topic = topicService.getTopicById(id);
        if (topic == null) {
            return "redirect:/";
        }
        User author = userService.findUserByUid(topic.getAuthorid());
        Avatar avatar = userService.findAvatarByUid(author.getId());
        model.addAttribute("topicContext", topic);
        model.addAttribute("topicAuthor", author);
        model.addAttribute("authorAvatar", avatar);
        model.addAttribute("praises", topicService.topicPraiseNumber(topic.getId()));
        model.addAttribute("isFavorites", favoritesService.isFavorites(new Favorites(user.getId(), topic.getId())));
        model.addAttribute("hasPraise", topicService.yiZan(new Praise(user.getId(), topic.getId())));
        List<VComments> commentsList = new ArrayList<>();
        if (page == null) {
            page = 0;
        }
        for (int i = 0; i <= page; i++) {
            commentsList.addAll(commentsService.findByTopicId(id, i).stream()
                    .map((comments -> new VComments(new VUser(userService.findUserByUid(comments.getUid())), userService.findAvatarByUid(comments.getUid()), comments)))
                    .peek(vComments -> vComments.setSubComments(commentsService.subComments(vComments.getId()).stream()
                            .map((comments -> new VComments(new VUser(userService.findUserByUid(comments.getUid())), userService.findAvatarByUid(comments.getUid()), comments))).collect(Collectors.toList()))
                    ).collect(Collectors.toList()));
        }
        model.addAttribute("stop", commentsService.findByTopicId(id, page + 1).size() == 0);
        model.addAttribute("comments", commentsList);
        model.addAttribute("page", page);
        model.addAttribute("louStart", new Storey(1));
        model.addAttribute("commentLooker", (Function<Integer, VComments>) targetId -> {
            Comments comment = commentsService.getCommentById(targetId);
            return new VComments(new VUser(userService.findUserByUid(comment.getUid())), userService.findAvatarByUid(comment.getUid()), comment);
        });
        model.addAttribute("userAvatar", userService.findAvatarByUid(((User) session.getAttribute("user")).getId()));
        return "/topic";
    }

    @GetMapping("/topic/comments/{tid}/{page}")
    public String topicComments(@PathVariable Integer tid, @PathVariable Integer page, Model model) {
        model.addAttribute("comments", commentsService.findByTopicId(tid, page).stream().map(
                (comments -> new VComments(new VUser(userService.findUserByUid(comments.getUid())), userService.findAvatarByUid(comments.getUid()), comments))
        ).collect(Collectors.toList()));
        model.addAttribute("stop", commentsService.findByTopicId(tid, page + 1).size() == 0);
        model.addAttribute("commentLooker", (Function<Integer, VComments>) targetId -> {
            Comments comment = commentsService.getCommentById(targetId);
            return new VComments(new VUser(userService.findUserByUid(comment.getUid())), userService.findAvatarByUid(comment.getUid()), comment);
        });
        model.addAttribute("louStart", new Storey(commentsPageNumber * page + 1));
        return "/topic::topicComments";
    }

    /**
     * 查看用户的帖子
     */
    @GetMapping("/topic/user/{userid}/{page}")
    public String loadUserTopics(@PathVariable Integer page, @PathVariable Integer userid, Model model) {
        List<Topic> topicByUserId = topicService.findTopicByUserId(userid, page).stream().peek(
                //仅展示部分内容
                topic -> topic.setContent(topic.getContent().replaceAll("^([\\s\\S]{0,32})([\\s\\S]*)$", "$1..."))
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

    @GetMapping("/topic/type/further/{id}/{number}")
    public String topicsShelves(@PathVariable Integer id, @PathVariable Integer number, Model model, HttpSession session) {
        topicReferralPage(id, number, model, session);
        return "/minindex::topicsShelves";
    }


    @PostMapping("/topic/reply/{tid}")
    public String addComments(String content, @Nullable Integer page, @PathVariable Integer tid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Comments comments = new Comments();
        comments.setTargetId(tid);
        comments.setContent(content);
        comments.setUid(user.getId());
        comments.setTargetType(0);
        comments.setParent(tid);
        commentsService.addComment(comments);
        return "redirect:/topic/read/" + tid + "?page=" + page;
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

    @GetMapping("/topic/write/page")
    public String writeTopic(Model model) {
        model.addAttribute("types", topicService.getTopicTypes());
        return "/edit";
    }

    @PostMapping("/topic/write/post")
    public String writeTopic(Topic topic, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Topic topic1 = topicService.addTopic(topic, user);
        if (topic1 != null) {
            return "redirect:/topic/read/" + topic1.getId();
        }
        return "redirect:/";
    }

    @PostMapping("/topic/user/favorites/add/{tid}")
    @ResponseBody
    public KeyAndValue<Boolean, String> addFavorites(@PathVariable Integer tid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (favoritesService.addToFavorites(new Favorites(user.getId(), tid)) > 0) {
            return new KeyAndValue<>(true, "收藏成功");
        } else {
            return new KeyAndValue<>(false, "收藏失败");
        }
    }

    @PostMapping("/topic/user/favorites/cancel/{tid}")
    @ResponseBody
    public KeyAndValue<Boolean, String> cancelFavorites(@PathVariable Integer tid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (favoritesService.cancel(new Favorites(user.getId(), tid)) > 0) {
            return new KeyAndValue<>(true, "取消成功");
        } else {
            return new KeyAndValue<>(false, "取消失败");
        }
    }

    @GetMapping("/topic/sou/{key}")
    @ResponseBody
    public List<VTopic> souSuo(@PathVariable @Nullable String key) {
        if (key == null) {
            return new ArrayList<>();
        }
        return topicService.findTopicLikeTitleAndType(key, null).stream().map(topic -> {
            VTopic vTopic = new VTopic(topic);
            vTopic.setType(topicService.findTopicTypeById(topic.getTypeid()));
            vTopic.setAuthor(new VUser(userService.findUserByUid(topic.getAuthorid())));
            return vTopic;
        }).collect(Collectors.toList());
    }

    @PostMapping("/topic/del")
    public String delTopic(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Topic topic = topicService.getTopicById(id);
        topicService.deleteTopic(topic, user);
        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/topic/praise/{id}")
    @ResponseBody
    public Boolean zan(@PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return topicService.addPraise(new Praise(user.getId(), id));
    }

    /**
     * 取消赞
     * 这就是拼音不要怀疑
     */
    @PostMapping("/topic/praise/cancel/{id}")
    @ResponseBody
    public Boolean quXiaoZan(@PathVariable Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return topicService.deletePraise(new Praise(user.getId(), id));
    }
}
