package jp.co.sss.practice.p04.q02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class Practice0402Controller {

	@RequestMapping(path = "/numguess/start", method = RequestMethod.GET)
	public String start(HttpSession session) {
		Integer hitnumber = (int) (Math.floor(Math.random()) * 9 + 1);
		session.setAttribute("hitnumber", hitnumber);
		System.out.println(hitnumber);
		return "practice04/02/numguess_start";
	}

	@RequestMapping(path = "/numguess/input", method = RequestMethod.GET)
	public String input() {
		return "practice04/02/numguess_input";
	}

	@RequestMapping(path = "/numguess/judge", method = RequestMethod.GET)
	public String judge(HttpSession session, Integer inputNum) {
		if (inputNum.equals(session.getAttribute("hitnumber"))) {
			return "redirect:/numguess/hit";
		} else {
			return "practice04/02/numguess_judge";
		}
	}

	@RequestMapping(path = "/numguess/hit", method = RequestMethod.GET)
	public String hit(Model model, HttpSession session) {
		String message = "当たりです！正解は" + session.getAttribute("hitnumber") + "でした！";
		model.addAttribute("message", message);
		session.invalidate();
		return "practice04/02/numguess_end";
	}

}
