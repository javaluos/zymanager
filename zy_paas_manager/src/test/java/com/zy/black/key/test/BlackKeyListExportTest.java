package com.zy.black.key.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zy.cms.service.syssettings.BlackKeyListService;
import com.zy.cms.vo.WebResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-application-context.xml" })
public class BlackKeyListExportTest {

	@Resource
	BlackKeyListService blackKeyListService;

	@Test
	public void testExport() {
		try {
			Map<String, Object> Parammap = new HashMap<String, Object>();
			Parammap.put("pageSize", 1000000);
			Parammap.put("pageOffset", 0);
			// 调用导出业务
			String realPath = "F:/zy_workspace/zy_paas_manager/src/main/webapp";
			WebResult webRs = blackKeyListService.export(Parammap, realPath, "", "txt");
			assertNotSame(-1, webRs.getCode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}

}
