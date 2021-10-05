package com.qa.spring.service;

import java.util.List;

import com.qa.spring.data.Marsupial;

public interface DFEService {

	public Marsupial getMarsupialByIndex(Integer id);

	public List<Marsupial> getAllMarsupials();

	public Marsupial createMarsupial(Marsupial marsupial);

	public Marsupial updateMarsupial(Marsupial marsupial, Integer id);

	public void deleteMarsupial(Integer id);
}
