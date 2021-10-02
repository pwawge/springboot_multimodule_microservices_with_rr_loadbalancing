package com.employee.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.EmployeeEntity;

@RestController
public class EmployeeInformationController {
	private Log logger = LogFactory.getLog(EmployeeInformationController.class);
	
	@Autowired
    Environment environment;

	// Initialize database
	private static final Map<Integer, EmployeeEntity> dataBase = new HashMap<>();
	static {
		dataBase.put(100, new EmployeeEntity(100, "Alex","zander"));
		dataBase.put(101, new EmployeeEntity(101, "Tom","Jerry"));
	}

	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
	public String getEmployeeDetails(@PathVariable int employeeId) {
		logger.info(String.format("Getting Details of Employee with id %s Port:  %s", employeeId,environment.getProperty("local.server.port")));
		return dataBase.get(employeeId).toString()+" Port : "+environment.getProperty("local.server.port");
	}
}
