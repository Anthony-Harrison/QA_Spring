package com.qa.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.spring.data.Marsupial;
import com.qa.spring.repo.MarsupialRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DFEServiceUnitTest {

	@Autowired
	private DFEServiceDB service;

	@MockBean
	private MarsupialRepo repo;

	@Test
	void testUpdate() {
		final Integer id = 1;

		Marsupial marsupial = new Marsupial(id, "Anth", "Kang", "Colour");
		Optional<Marsupial> optMarsupial = Optional.of(marsupial);

		Marsupial newMarsupial = new Marsupial(id, "Bill", "mouse", "Green");

		Mockito.when(this.repo.findById(id)).thenReturn(optMarsupial);
		Mockito.when(this.repo.save(newMarsupial)).thenReturn(newMarsupial);

		assertEquals(newMarsupial, this.service.updateMarsupial(newMarsupial, marsupial.getId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newMarsupial);
	}

	@Test
	void testCreate() {
		Marsupial marsupial = new Marsupial(1, "Anth", "Kang", "Colour");

		Mockito.when(this.repo.save(marsupial)).thenReturn(marsupial);
		assertEquals(marsupial, this.service.createMarsupial(marsupial));

		Mockito.verify(this.repo, Mockito.times(1)).save(marsupial);
	}

	@Test
	void testGetAllMarsupials() {
		final Marsupial savedMarsupial = new Marsupial(1, "Wally", "Wallabee", "grey");

		List<Marsupial> marsupialList = new ArrayList<>();
		marsupialList.add(savedMarsupial);

		Mockito.when(this.repo.findAll()).thenReturn(marsupialList);
		assertEquals(marsupialList, this.service.getAllMarsupials());
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testGetMarsupialByIndex() {
		final Integer id = 1;
		Marsupial marsupial = new Marsupial(id, "Anth", "Kang", "Colour");

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(marsupial));
		assertEquals(marsupial, this.service.getMarsupialByIndex(id));

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

}
