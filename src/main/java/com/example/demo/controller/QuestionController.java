package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.Comment;
import com.example.demo.bean.GitHubUser;
import com.example.demo.bean.Question;
import com.example.demo.bean.tag;
import com.example.demo.bean.DTO.CommentDTO;
import com.example.demo.bean.DTO.QuestionDTO;
import com.example.demo.service.CommentService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.QuestionService;
import com.example.demo.util.BaseContext;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	NotificationService notificationService;
	
	
	/**
	 * 去问题发布页
	 * @date 2024年6月13日 下午5:19:26
	 * @return
	 */
	@GetMapping("/publish")
	public String ToPublish() {
		return "publish";
	}
	
	/**
	 * 修改问题
	 * @date 2024年6月18日 上午8:21:38
	 * @param id
	 * @return
	 */
	@GetMapping("/publish/{id}")
	public String ToPublish(@PathVariable Integer id,Model model) {
		Question question = questionService.seletById(id);
		model.addAttribute("qUp", question);
		return "publish";
	}
	
	
	/**
	 * 发布问题
	 * @date 2024年6月17日 上午10:46:03
	 * @param question
	 * @param req
	 * @return
	 */
	@PostMapping("/publish")
	public String addQuestion(Question question,HttpServletRequest req) {
		if(question.getId()==null) {
			questionService.addQuestion(question);
		}else {
			question.setModifiedTime(LocalDateTime.now());
			questionService.updateQuestion(question);
		}
		
		return "redirect:/";
	}
	
	/**
	 * 点击问题，进入问题详情页
	 * @date 2024年6月18日 上午8:06:48
	 * @param id
	 * @param model
	 * @return
	 */
	
	@GetMapping("/{id}")
	public String questionDeatil(@PathVariable Integer id,@RequestParam(required = false) Integer notificationId,Model model) {
		QuestionDTO questionDTO=questionService.seletDtoById(id);
		
		//浏览数+1
		questionService.viewCountIncrease(id);
		//删除对应的通知
		if(BaseContext.getCurrentId() != null && notificationId!=null) {
			notificationService.removeOne(notificationId);
		}
		
		List<CommentDTO> commentDTO=commentService.selectCommentDTO(id,1);
		List<Question> relQuestion=questionService.selectQuestionByTags(questionDTO.getTag(),id);
		
		model.addAttribute("questionDTO", questionDTO);
		model.addAttribute("commentDTO",commentDTO);
		//相关搜索问题
		model.addAttribute("relQuestion",relQuestion);
		return "question";
	}
	
	
	/**
	 * 测试 用枚举类标识问题的标签
	 * @date 2024年10月26日 下午2:56:07
	 * @param model
	 * @return
	 */
	@GetMapping("/test")
	public String test(Model model) {
		model.addAttribute("tags",tag.values());
		return "testht";
	}
}
