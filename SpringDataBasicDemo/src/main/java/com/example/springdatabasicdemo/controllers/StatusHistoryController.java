package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.StatusHistoryDto;
import com.example.springdatabasicdemo.services.StatusHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status_history")
public class StatusHistoryController {
	@Autowired
	private StatusHistoryService statusHistoryService;

	@GetMapping("/{id}")
	public Iterable<StatusHistoryDto> getHistoryByParcelId(@PathVariable Integer id) {
		return statusHistoryService.getParcelHistoryByParcelId(id);
	}
}
