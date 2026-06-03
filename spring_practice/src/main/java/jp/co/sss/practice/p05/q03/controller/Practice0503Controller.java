package jp.co.sss.practice.p05.q03.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.practice.p05.bean.FruitsSeasonBean;
import jp.co.sss.practice.p05.entity.FruitsSeason;
import jp.co.sss.practice.p05.repository.FruitsSeasonRepository;

@Controller
public class Practice0503Controller {

	@Autowired
	FruitsSeasonRepository repository;

	@RequestMapping(path = "/fruits/list/sort/id", method = RequestMethod.GET)
	public String showFruitByOrderByFruitId(Model model) {
		model.addAttribute("fruits_season", repository.findAllByOrderByFruitId());
		return "practice05/03/fruits_list";
	}

	@RequestMapping(path = "/fruits/detail/{fruitId}", method = RequestMethod.GET)
	public String showFruitDetail(@PathVariable Integer fruitId, Model model) {
		FruitsSeason fruitsSeason = repository.getReferenceById(fruitId);
		FruitsSeasonBean fruitsSeasonBean = new FruitsSeasonBean();
		BeanUtils.copyProperties(fruitsSeason, fruitsSeasonBean);
		model.addAttribute("fruit", fruitsSeasonBean);
		return "practice05/03/fruit_detail";
	}

}
