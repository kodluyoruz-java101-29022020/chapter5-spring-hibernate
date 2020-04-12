package com.company.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.project.dao.TitleDAO;
import com.company.project.dao.entity.Title;

@Component
public class TitleService {

	@Autowired
	private TitleDAO titleDAO;
	
	
	public List<Title> findAll() {
		
		return this.titleDAO.findAll();
	}
	
}
