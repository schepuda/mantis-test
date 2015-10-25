package com.example.tests;

import static org.testng.Assert.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.framework.AccountHelper;
import com.example.framework.JamesHelper;
import com.example.framework.User;

public class SignUpTest extends TestBase {
	
	public User user = new User().setLogin("testuser1").setPassword("123456")
				.setEmail("testuser1@localhost.localdomain");
	private JamesHelper james;
	private AccountHelper accHelper;
	
	@BeforeClass	
	public void createMailUser() {
		james = app.getJamesHelper();
		accHelper = app.getAccountHelper();
		if (! james.doesUserExist(user.login)) {
			james.createUser(user.login, user.password);
		}
	}
	
	
	@AfterClass
	public void deleteMailUser() {
		if (james.doesUserExist(user.login)) {
			james.deleteUser(user.login);
		}
	}
	
	@Test
	public void newUserShouldSignup() {
		accHelper.signup(user);
		accHelper.login(user);
		assertThat(accHelper.loggedUser(), equalTo(user.login));
	}
	
	//@Test
	public void existingUserShouldSignup() {
		try {
			accHelper.signup(user);
		} catch (Exception e) {
			assertThat(e.getMessage(), containsString(""));
			return;
		}
		fail("Exeption expected");
	}
	
}
