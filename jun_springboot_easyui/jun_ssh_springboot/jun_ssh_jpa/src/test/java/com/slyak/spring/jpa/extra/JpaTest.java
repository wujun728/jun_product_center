package com.slyak.spring.jpa.extra;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.common.utils.MyPrinter;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:stormning@163.com">stormning</a>
 * @version V1.0, 16/3/15.
 */

public class JpaTest extends BaseTest{
	@Autowired
	private SampleRepository sampleRepository;

	@Before
	public void addSomeSample() {
//		sampleRepository.deleteAll();
//		for (int i = 0; i < 10; i++) {
//			Sample sample = new Sample();
//			sample.setContent("hello world" + i);
//			sampleRepository.save(sample);
//		}
//		Sample temp =new Sample();
//		temp.setContent("hello world1");
//		sampleRepository.save(temp);
	}

	@Test
	public void findPage() {
		Page<Sample> samples = sampleRepository.findByContent("%world1%", new PageRequest(0, 100));
		MyPrinter.printJson(samples);
		Assert.assertTrue(samples.getTotalElements() == 2);
	}

	@Test
	public void findList() {
		List<Sample> samples = sampleRepository.aaaContent("%world1%");
		MyPrinter.printJson(samples);
		Assert.assertTrue(samples.size()== 2);
	}

	@Test
	public void countByTemplateQuery() {
		long count = sampleRepository.countContent("%world1%");
		Assert.assertTrue(count == 2);
	}

	@Test
	public void findByTemplateQueryAndReturnDTOs() {
		List<SampleDTO> dtos = sampleRepository.findDtos();
		MyPrinter.printJson(dtos);
		Assert.assertTrue(dtos.size() == 11);
	}

	@Test
	public void findByTemplateQueryWithTemplateQueryObject() {
		SampleQuery sq = new SampleQuery();
		sq.setContent("world1");
		List<Sample> samples = sampleRepository.findByTemplateQueryObject(sq, null);
		MyPrinter.printJson(samples);
		Assert.assertTrue(samples.size() == 2);
	}

	@Test
	public void findBySpringElQuery() {
		List<Sample> dtos = sampleRepository.findDtos2("%world1%");
		MyPrinter.printJson(dtos);
		Assert.assertTrue(dtos.size() == 2);
	}

	@Test
	public void findMap(){
		List<Map<String,Object>> listMaps = sampleRepository.findMap();
		MyPrinter.printJson(listMaps);
		Assert.assertTrue(listMaps.size() == 11);
	}

}
