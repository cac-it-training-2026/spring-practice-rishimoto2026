package jp.co.sss.practice.p05.q06.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.practice.p05.bean.FruitsSeasonBean;
import jp.co.sss.practice.p05.entity.FruitsSeason;
import jp.co.sss.practice.p05.form.FruitsSeasonForm;
import jp.co.sss.practice.p05.repository.FruitsSeasonRepository;

@Controller
public class Practice0506Controller {

	@Autowired
	FruitsSeasonRepository repository;

	@RequestMapping(path = "/fruits/update", method = RequestMethod.GET)
	public String fruitsUpdate(Model model) {
		model.addAttribute("fruitsList", repository.findAllByOrderByFruitId());
		return "practice05/06/fruit_select";
	}

	@RequestMapping(path = "/fruits/update/input", method = RequestMethod.POST)
	public String fruitsInput(Integer fruitId, Model model) {
		FruitsSeason fruitsSeason = repository.getReferenceById(fruitId);
		FruitsSeasonBean fruitsSeasonBean = new FruitsSeasonBean();
		BeanUtils.copyProperties(fruitsSeason, fruitsSeasonBean);
		model.addAttribute("fruitsSeason", fruitsSeasonBean);
		return "practice05/06/fruit_input";
	}

	@RequestMapping(path = "/fruits/update/complete/{fruitId}", method = RequestMethod.POST)
	public String fruitsUpdateComplete(@PathVariable Integer fruitId, FruitsSeasonForm fruitfForm, Model model) {

		FruitsSeason fruitsSeason = new FruitsSeason();
		BeanUtils.copyProperties(fruitfForm, fruitsSeason);
		fruitsSeason = repository.save(fruitsSeason);
		FruitsSeasonBean fruitsSeasonBean = new FruitsSeasonBean();
		BeanUtils.copyProperties(fruitsSeason, fruitsSeasonBean);
		model.addAttribute("fruitsSeason", fruitsSeasonBean);
		return "practice05/06/fruit_detail";
	}

}
