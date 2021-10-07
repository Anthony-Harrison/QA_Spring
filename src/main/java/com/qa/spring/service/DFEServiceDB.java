package com.qa.spring.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.spring.data.Marsupial;
import com.qa.spring.exception.MarsupialNotFoundException;
import com.qa.spring.repo.MarsupialRepo;

@Primary
@Service
public class DFEServiceDB implements DFEService {
	private MarsupialRepo repo;

	public DFEServiceDB(MarsupialRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Marsupial getMarsupialByIndex(Integer id) {
		return this.repo.findById(id).orElseThrow(MarsupialNotFoundException::new);

	}

	@Override
	public List<Marsupial> getAllMarsupials() {
		return this.repo.findAll();
	}

	@Override
	public Marsupial createMarsupial(Marsupial marsupial) {
		return this.repo.save(marsupial);
	}

	@Override
	public Marsupial updateMarsupial(Marsupial marsupial, Integer id) {
		Marsupial toUpdate = this.repo.findById(id).get();
		toUpdate.setColour(marsupial.getColour());
		toUpdate.setName(marsupial.getName());
		toUpdate.setSpecies(marsupial.getSpecies());

		return this.repo.save(toUpdate);
	}

	@Override
	public void deleteMarsupial(Integer id) {
		this.repo.deleteById(id);

	}

}
