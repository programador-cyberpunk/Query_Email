package emailService;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.conig.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;
public class Email_teste {
	private static final String TOKEN = "9413ac7fb4bd5565ff2880778ae77461\r\n"
			+ "";
	public static void main(String[] args) {
		final MailtrapConfig config = new MailtrapConfig.Builder().token(TOKEN).build();
		final MailtrapCliente client = MailtrapCLientFactory.createMailtrapClient(config);
		final MailtrapMail mail = MailtrapMail.builder().
				from(new Adress("xalon@demomailtrap.co", "Mail Teste")).
				to(List.of(new Adress("barbosa.hugo@aluno.ifsp.eu.br"))).
				subject("Hail Satan").text("Xow,funcionou!").
				category("Integration test").build();
		try {
			System.out.println(client.sen(email));
		}catch (Exception e){
			System.out.println("Caught exception : " +e);
		}
		
	}

}
