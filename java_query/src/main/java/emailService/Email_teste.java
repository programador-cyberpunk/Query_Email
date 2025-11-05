package emailService;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;

public class Email_teste {
	
	private static final String TOKEN = "9413ac7fb4bd5565ff2880778ae77461";

	public static void main(String[] args) {
		final MailtrapConfig config = new MailtrapConfig.Builder().token(TOKEN).build();
		
		final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);
		
		final MailtrapMail mail = MailtrapMail.builder().
				from(new Address("xalon@demomailtrap.co", "Mail Teste")).
				to(List.of(new Address("barbosa.hugo@aluno.ifsp.edu.br"))).
				subject("Hail Satan").text("Xow,funcionou!").
				category("Integration test").build();
		try {
			System.out.println(client.send(mail));
		}catch (Exception e){
			System.out.println("Caught exception : " +e);
		}
	}
}