package pl.graphdebt.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import pl.graphdebt.client.GreetingService;
import pl.graphdebt.shared.Bill;
import pl.graphdebt.shared.Money;
import pl.graphdebt.shared.Person;
import pl.graphdebt.shared.Position;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	   static {
	        ObjectifyService.register(Bill.class);
	        ObjectifyService.register(Money.class);
	        ObjectifyService.register(Person.class);
	        ObjectifyService.register(Position.class);
	    }

	public String greetServer(String input) throws IllegalArgumentException {
		String[] parts = input.split(";");
		int partIdx = 0;

		final String billName = parts[partIdx++];
		List<Position> billPos = new ArrayList<Position>();
		while (partIdx < parts.length) {
			String[] posParts = parts[partIdx++].split("=", 2);
			Position pos = new Position();
			pos.setName(posParts[0]);
			pos.setAmount(Double.valueOf(posParts[1]));
			billPos.add(pos);
		}
		
		Map<Key<Position>, Position> billSavedPos = ofy().save().entities(billPos).now();
		Bill bill = new Bill();
		bill.setName(billName);
		bill.setPositions(new ArrayList<Key<Position>>(billSavedPos.keySet()));
		ofy().save().entity(bill).now();
		return "" + bill.getId().toString();
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
