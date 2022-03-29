package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
	@Test
	public void TC_01() {
		String employee = "Tran van em";
		// Dùng để kiểm tra 1 điều kiện trả về là đúng(true)
		Assert.assertTrue(employee.equals("Tran van em"));
		//Assert.assertTrue(employee.equals("Tran van e"),"Employee not equal");
		// Dùng để kiểm tra 1 điều kiện trả về là sai(false)
		Assert.assertFalse(employee.equals("dsdsd"));

	}
}
