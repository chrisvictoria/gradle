package co.ceiba.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

import co.ceiba.domain.Person;
import co.ceiba.testdatabuilder.PersonTestDataBuilder;

public class NotifyPersonServiceTest {
	private NotifyPersonService notifyPersonService;
	private EmailService emailService;
	
	@Before
	public void setup(){
		emailService = new EmailService();
		notifyPersonService = new NotifyPersonService(emailService);
	}
	
	@Test
	public void notifyTest(){
		Person person = new PersonTestDataBuilder().build();
		String msg = notifyPersonService.notify(person);
		Assert.assertNull(msg);
	}
	
	@Test
	public void notify2Test(){
		emailService = Mockito.mock(EmailService.class);	
		Mockito.when(emailService.sendMail(Mockito.anyString())).thenReturn("Hola mundo");
		Person person = new PersonTestDataBuilder().build();
		String msg = notifyPersonService.notify(person);
		Assert.assertNotNull(msg);
	}
}
