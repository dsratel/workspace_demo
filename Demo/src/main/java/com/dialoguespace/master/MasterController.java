package com.dialoguespace.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/master")
public class MasterController {
	
	@Autowired
	MasterService masterService;
	
	@GetMapping(value="/home")
	public String home(Model model) {
		return "/master/masterHome";
	}
}